package report.friction.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import report.friction.FrictionReportApiApplication;
import report.friction.dto.AreaInitDTO;
import report.friction.dto.ClimbingAreaDTO;
import report.friction.dto.ClimbingAreaMapper;
import report.friction.dto.ClimbingAreaMapperImpl;
import report.friction.entities.ClimbingAreaEntity;
import report.friction.repositories.ClimbingAreaRepository;

import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    private List<ClimbingAreaEntity> getMockAreas(){
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

    private ClimbingAreaEntity getMockArea(){
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
    public void testGetAreasInit(){
        List<ClimbingAreaEntity> areas = getMockAreas();

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
    public void testGetAreaMapData_AllUpToDate(){}

    @Test
    public void testGetAreaMapData_PartialDataRefresh(){}

    @Test
    public void testGetAreaMapData_FullDataRefresh(){}

    @Test
    public void testGetAreaMapData_JsonError(){}

    @Test
    public void testGetAreaMapData_OpenWeatherError(){}

    @Test
    public void testGetClimbingAreaData_UpToDate(){}

    @Disabled
    @Test
    public void testGetClimbingAreaData_RefreshData() throws Exception{
        ClimbingAreaEntity area = getMockArea();
        HttpResponse mockResponse = mock(HttpResponse.class);
        ObjectMapper mapper = new ObjectMapper();
        URL ledaResponseFile = getClass().getClassLoader().getResource("test/ledaExample.json");
        String ledaResponse = new String(Files.readAllBytes(Paths.get(ledaResponseFile.toURI())));

        when(climbingAreaRepository.findByAreaName("leda"))
                .thenReturn(area);
        when(client.send(any(HttpRequest.class), any()))
                .thenReturn(mockResponse);
        when(mockResponse.body())
                .thenReturn(ledaResponse);
        when(climbingAreaRepository.save(area))
                .thenReturn(area);

        ClimbingAreaDTO response = climbingAreaService.getClimbingAreaData("leda");
        System.out.println(response);

    }

    @Test
    public void testGetClimbingAreaData_AreaNotFound(){}

    @Test
    public void testGetClimbingAreaData_JsonError(){}

    @Test
    public void testGetClimbingAreaData_OpenWeatherError(){}

}