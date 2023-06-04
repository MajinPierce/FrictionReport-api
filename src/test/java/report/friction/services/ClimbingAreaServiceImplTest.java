package report.friction.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestPropertySource;
import report.friction.exceptions.AreaNotFoundException;
import report.friction.exceptions.JacksonMappingException;
import report.friction.exceptions.OpenWeatherException;
import report.friction.models.ClimbingAreaEntity;
import report.friction.repositories.ClimbingAreaRepository;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = "OPEN_WEATHER_API_KEY=dummyKey")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ClimbingAreaServiceImplTest {

    @InjectMocks
    private ClimbingAreaServiceImpl climbingAreaService;

    @Mock
    private ClimbingAreaRepository climbingAreaRepository;

    private ClimbingAreaEntity mockArea;

    @BeforeEach
    public void setup(){
        mockArea = new ClimbingAreaEntity();
        mockArea.setId(1);
        mockArea.setAreaName("Leda");
        mockArea.setLat(35.23649);
        mockArea.setLon(-85.22971);
    }

    @Test
    public void shouldExpectAreaNotFoundException(){
        String unknownAreaName = "horse";
        when(climbingAreaRepository.findByAreaName(anyString())).thenReturn(null);
        Throwable exception = Assertions.assertThrows(AreaNotFoundException.class,
                () -> climbingAreaService.getClimbingAreaData(unknownAreaName));
        assertEquals("Climbing area not found: " + unknownAreaName, exception.getMessage());
    }

    //TODO make unit test work
    @Disabled
    @Test
    public void shouldExpectClimbingAreaEntityReturnedWhenUpdatedAtIsNull() throws Exception{}

    @Test
    public void shouldExpectClimbingAreaEntityReturnedWhenUpdatedAtNotNull(){
        Instant mockInstant = Instant.ofEpochSecond(Instant.now().getEpochSecond() - 100);
        mockArea.setUpdatedAt(mockInstant);
        when(climbingAreaRepository.findByAreaName(anyString())).thenReturn(mockArea);
        assert(climbingAreaService.getClimbingAreaData("Leda")).equals(mockArea);
    }

    //TODO make unit test work
    @Disabled
    @Test
    public void shouldExpectHttpResponseAndClimbingAreaEntityReturned() throws Exception{}

    //TODO make unit test work
    @Disabled
    @Test
    public void shouldExpectOpenWeatherException() throws Exception{}

    //TODO make unit test work
    @Disabled
    @Test
    public void shouldExpectJacksonMappingException() throws Exception{}

}