package report.friction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import report.friction.models.ClimbingAreaEntity;
import report.friction.repositories.ClimbingAreaRepository;

@Service
public class ClimbingAreaServiceImpl implements ClimbingAreaService{

    @Value("${OPEN_WEATHER_API_KEY}")
    private String openWeatherApiKey;
    private static final String OPEN_WEATHER_DOMAIN = "https://api.openweathermap.org/data/3.0/onecall?";
    private static final Integer CACHING_TIMEOUT_SECONDS = 600;

    private final ClimbingAreaRepository climbingAreaRepository;

    @Autowired
    public ClimbingAreaServiceImpl(ClimbingAreaRepository climbingAreaRepository){
        this.climbingAreaRepository = climbingAreaRepository;
    }

    @Override
    public ClimbingAreaEntity getClimbingAreaData(String areaName) {
        ClimbingAreaEntity area = climbingAreaRepository.findByAreaName(areaName);
        //if(area.getUpdatedAt() == null || Instant.now().getEpochSecond() - area.getUpdatedAt().getEpochSecond() > CACHING_TIMEOUT_SECONDS){
            try{
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder(
                                URI.create(buildApiUrl(area)))
                        .header("Content-Type", "application/json").build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(request);
                System.out.println(response.body());
                //FIXME creates new rows on the weather column for each call
                // - only updates the area row (as intended)
                // - creates new rows of weather data for each area which means orphan data
                ObjectMapper objectMapper = new ObjectMapper();
                area = objectMapper.readerForUpdating(area).readValue(response.body());
                climbingAreaRepository.save(area);
                return area;
            } catch(Exception e){
                System.out.println(e);
                return null;
            }
        //} else {
        //    System.out.println("else");
        //}

    }

    private String buildApiUrl(ClimbingAreaEntity area){
        System.out.println(area.getAreaName());
        //openWeatherApiKey set in env vars
        return String.format("%slat=%f&lon=%f&exclude=minutely&units=imperial&appid=%s",
                OPEN_WEATHER_DOMAIN , area.getLat(), area.getLon(), openWeatherApiKey);
    }
}
