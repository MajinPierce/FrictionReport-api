package report.friction.services;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestPropertySource;
import report.friction.exceptions.AreaNotFoundException;

import report.friction.dao.ClimbingAreaEntity;
import report.friction.repositories.ClimbingAreaRepository;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
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