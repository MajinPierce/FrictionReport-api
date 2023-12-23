package report.friction.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.approvaltests.JsonJacksonApprovals;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import report.friction.dto.*;
import report.friction.entities.ClimbingAreaEntity;
import report.friction.exceptions.custom.AreaNotFoundException;
import report.friction.exceptions.custom.JacksonMappingException;
import report.friction.exceptions.custom.OpenWeatherException;
import report.friction.repositories.ClimbingAreaRepository;

import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClimbingAreaServiceImplTest {

    private ClimbingAreaRepository climbingAreaRepository = mock(ClimbingAreaRepository.class);
    private HttpClient client = mock(HttpClient.class);
    private ClimbingAreaMapper climbingAreaMapper = new ClimbingAreaMapperImpl();
    private String onecall = "https://api.openweathermap.org/data/3.0/onecall";
    private String apiKey = "API_KEY_HERE";
    private String units = "imperial";

    private ClimbingAreaServiceImpl climbingAreaService = new ClimbingAreaServiceImpl(
            climbingAreaRepository,
            climbingAreaMapper,
            client,
            onecall,
            apiKey,
            units
    );

    private static ObjectMapper mapper;

    @BeforeAll
    public static void init(){
        mapper = new ObjectMapper();
    }

    private List<ClimbingAreaEntity> getMockUninitializedAreas(){
        ClimbingAreaEntity area1 = new ClimbingAreaEntity();
        area1.setId(1);
        area1.setAreaName("leda");
        area1.setFullName("Leda");
        area1.setMountainProjectUrl("https://www.mountainproject.com/area/105888074/leda");
        area1.setState("Tennessee");
        area1.setLat(35.2356);
        area1.setLon(-85.2265);

        ClimbingAreaEntity area2 = new ClimbingAreaEntity();
        area2.setId(2);
        area2.setAreaName("redrivergorge");
        area2.setFullName("Red River Gorge");
        area2.setMountainProjectUrl("https://www.mountainproject.com/area/105841134/red-river-gorge");
        area2.setState("Kentucky");
        area2.setLat(37.67745);
        area2.setLon(-83.68217);

        return List.of(area1, area2);
    }

    private ClimbingAreaEntity getMockUninitializedArea(){
        ClimbingAreaEntity area1 = new ClimbingAreaEntity();
        area1.setId(1);
        area1.setAreaName("leda");
        area1.setFullName("Leda");
        area1.setMountainProjectUrl("https://www.mountainproject.com/area/105888074/leda");
        area1.setState("Tennessee");
        area1.setLat(35.2356);
        area1.setLon(-85.2265);

        return area1;
    }

    @Test
    void testGetAreasInit(){
        List<ClimbingAreaEntity> areas = getMockUninitializedAreas();

        when(climbingAreaRepository.findAll())
                .thenReturn(areas);

        List<AreaInitDTO> response = climbingAreaService.getAreasInit();

        verify(climbingAreaRepository, times(1)).findAll();
        assertEquals(areas.size(), response.size());
        Set<String> areaNames = areas.stream()
                .map(ClimbingAreaEntity::getAreaName)
                .collect(Collectors.toSet());
        response.forEach(areaInitDTO -> assertTrue(areaNames.contains(areaInitDTO.name())));
        Set<String> areaFullNames = areas.stream()
                .map(ClimbingAreaEntity::getFullName)
                .collect(Collectors.toSet());
        response.forEach(areaInitDTO -> assertTrue(areaFullNames.contains(areaInitDTO.fullName())));
        Set<String> areaStates = areas.stream()
                .map(ClimbingAreaEntity::getState)
                .collect(Collectors.toSet());
        response.forEach(areaInitDTO -> assertTrue(areaStates.contains(areaInitDTO.state())));
    }

    @Test
    void testGetAreaMapData_AllUpToDate() throws Exception{
        List<ClimbingAreaEntity> areas = getMockUninitializedAreas();

        URL ledaResponseFile = getClass().getClassLoader().getResource("mockResponses/ledaExample.json");
        String ledaResponse = new String(Files.readAllBytes(Paths.get(ledaResponseFile.toURI())));
        URL rrgResponseFile = getClass().getClassLoader().getResource("mockResponses/rrgExample.json");
        String rrgResponse = new String(Files.readAllBytes(Paths.get(rrgResponseFile.toURI())));

        ClimbingAreaEntity leda = areas.get(0);
        leda = mapper.readerForUpdating(leda).readValue(ledaResponse);
        leda.onUpdate();
        ClimbingAreaEntity rrg = areas.get(1);
        rrg = mapper.readerForUpdating(rrg).readValue(rrgResponse);
        rrg.onUpdate();

        List<ClimbingAreaEntity> updatedAreas = List.of(leda, rrg);

        when(climbingAreaRepository.findAll())
                .thenReturn(updatedAreas);

        List<AreaMapDTO> response = climbingAreaService.getAreaMapData();
        JsonJacksonApprovals.verifyAsJson(response);
    }

    @Test
    void testGetAreaMapData_PartialDataRefresh() throws Exception{
        List<ClimbingAreaEntity> areas = getMockUninitializedAreas();
        HttpResponse mockLedaResponse = mock(HttpResponse.class);
        URL ledaResponseFile = getClass().getClassLoader().getResource("mockResponses/ledaExample.json");
        String ledaResponse = new String(Files.readAllBytes(Paths.get(ledaResponseFile.toURI())));
        URL rrgResponseFile = getClass().getClassLoader().getResource("mockResponses/rrgExample.json");
        String rrgResponse = new String(Files.readAllBytes(Paths.get(rrgResponseFile.toURI())));

        ClimbingAreaEntity rrg = areas.get(1);
        rrg = mapper.readerForUpdating(rrg).readValue(rrgResponse);
        rrg.onUpdate();

        List<ClimbingAreaEntity> partialAreas = List.of(areas.get(0), rrg);

        when(climbingAreaRepository.findAll())
                .thenReturn(partialAreas);
        when(client.send(any(HttpRequest.class), any()))
                .thenReturn(mockLedaResponse)
                .thenThrow(new RuntimeException("Should only be called once"));
        when(mockLedaResponse.body())
                .thenReturn(ledaResponse);
        when(climbingAreaRepository.saveAll(areas))
                .thenReturn(partialAreas);

        List<AreaMapDTO> response = climbingAreaService.getAreaMapData();
        JsonJacksonApprovals.verifyAsJson(response);
    }

    @Test
    void testGetAreaMapData_FullDataRefresh() throws Exception{
        List<ClimbingAreaEntity> areas = getMockUninitializedAreas();
        HttpResponse mockLedaResponse = mock(HttpResponse.class);
        HttpResponse mockRrgResponse = mock(HttpResponse.class);
        URL ledaResponseFile = getClass().getClassLoader().getResource("mockResponses/ledaExample.json");
        String ledaResponse = new String(Files.readAllBytes(Paths.get(ledaResponseFile.toURI())));
        URL rrgResponseFile = getClass().getClassLoader().getResource("mockResponses/rrgExample.json");
        String rrgResponse = new String(Files.readAllBytes(Paths.get(rrgResponseFile.toURI())));

        when(climbingAreaRepository.findAll())
                .thenReturn(areas);
        when(client.send(any(HttpRequest.class), any()))
                .thenAnswer(invocation -> {
                    HttpRequest request = (HttpRequest) invocation.getArgument(0);
                    HttpResponse<String> response;
                    if(request.uri().getQuery().contains("35.2356") &&
                            request.uri().getQuery().contains("-85.2265")){
                        response = mockLedaResponse;
                    } else if (request.uri().getQuery().contains("37.67745") &&
                            request.uri().getQuery().contains("-83.68217")) {
                        response = mockRrgResponse;
                    } else {
                        throw new RuntimeException("nothing matches");
                    }
                    return response;
                });
        when(mockLedaResponse.body())
                .thenReturn(ledaResponse);
        when(mockRrgResponse.body())
                .thenReturn(rrgResponse);
        when(climbingAreaRepository.saveAll(areas))
                .thenReturn(areas);

        List<AreaMapDTO> response = climbingAreaService.getAreaMapData();
        JsonJacksonApprovals.verifyAsJson(response);
    }

    @Test
    void testGetAreaMapData_JsonError() throws Exception{
        List<ClimbingAreaEntity> areas = getMockUninitializedAreas();
        HttpResponse mockResponse = mock(HttpResponse.class);
        String nonMappableResponse = "{ \"cat1\":\"percy\", \"cat2\":\"artemis\"}";

        when(climbingAreaRepository.findAll())
                .thenReturn(areas);
        when(client.send(any(HttpRequest.class), any()))
                .thenReturn(mockResponse);
        when(mockResponse.body())
                .thenReturn(nonMappableResponse);

        assertThrows(JacksonMappingException.class, () -> climbingAreaService.getAreaMapData());
    }

    @Test
    void testGetAreaMapData_OpenWeatherError() throws Exception{
        List<ClimbingAreaEntity> areas = getMockUninitializedAreas();

        when(climbingAreaRepository.findAll())
                .thenReturn(areas);
        when(client.send(any(HttpRequest.class), any()))
                .thenThrow(new InterruptedException("uh oh open weather broke"));

        assertThrows(OpenWeatherException.class, () -> climbingAreaService.getAreaMapData());
    }

    @Test
    void testGetClimbingAreaData_UpToDate() throws Exception{
        ClimbingAreaEntity area = getMockUninitializedArea();

        URL ledaResponseFile = getClass().getClassLoader().getResource("mockResponses/ledaExample.json");
        String ledaResponse = new String(Files.readAllBytes(Paths.get(ledaResponseFile.toURI())));
        area = mapper.readerForUpdating(area).readValue(ledaResponse);
        area.onUpdate();

        when(climbingAreaRepository.findByAreaName("leda"))
                .thenReturn(Optional.of(area));

        ClimbingAreaDTO response = climbingAreaService.getClimbingAreaData("leda");
        JsonJacksonApprovals.verifyAsJson(response);
    }

    @Test
    void testGetClimbingAreaData_RefreshData() throws Exception{
        ClimbingAreaEntity area = getMockUninitializedArea();
        HttpResponse mockResponse = mock(HttpResponse.class);
        URL ledaResponseFile = getClass().getClassLoader().getResource("mockResponses/ledaExample.json");
        String ledaResponse = new String(Files.readAllBytes(Paths.get(ledaResponseFile.toURI())));

        when(climbingAreaRepository.findByAreaName("leda"))
                .thenReturn(Optional.of(area));
        when(client.send(any(HttpRequest.class), any()))
                .thenReturn(mockResponse);
        when(mockResponse.body())
                .thenReturn(ledaResponse);
        when(climbingAreaRepository.save(area))
                .thenReturn(area);

        ClimbingAreaDTO response = climbingAreaService.getClimbingAreaData("leda");
        JsonJacksonApprovals.verifyAsJson(response);

    }

    @Test
    void testGetClimbingAreaData_AreaNotFound(){
        when(climbingAreaRepository.findByAreaName(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(AreaNotFoundException.class, () -> climbingAreaService.getClimbingAreaData("artemis"));
    }

    @Test
    void testGetClimbingAreaData_JsonError() throws Exception {
        ClimbingAreaEntity area = getMockUninitializedArea();
        HttpResponse mockResponse = mock(HttpResponse.class);
        String nonMappableResponse = "{ \"cat1\":\"percy\", \"cat2\":\"artemis\"}";

        when(climbingAreaRepository.findByAreaName("leda"))
                .thenReturn(Optional.of(area));
        when(client.send(any(HttpRequest.class), any()))
                .thenReturn(mockResponse);
        when(mockResponse.body())
                .thenReturn(nonMappableResponse);

        assertThrows(JacksonMappingException.class, () -> climbingAreaService.getClimbingAreaData("leda"));
    }

    @Test
    void testGetClimbingAreaData_OpenWeatherError() throws Exception{
        ClimbingAreaEntity area = getMockUninitializedArea();

        when(climbingAreaRepository.findByAreaName("leda"))
                .thenReturn(Optional.of(area));
        when(client.send(any(HttpRequest.class), any()))
                .thenThrow(new InterruptedException("uh oh open weather broke"));

        assertThrows(OpenWeatherException.class, () -> climbingAreaService.getClimbingAreaData("leda"));

    }

}