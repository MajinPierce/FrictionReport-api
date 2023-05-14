package report.friction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

import report.friction.dao.ClimbingAreaEntity;
import report.friction.dao.ClimbingAreaRepository;
import report.friction.models.ClimbingAreaObj;

@Service
public class ClimbingAreaServiceImpl implements ClimbingAreaService{

    @Value("${OPEN_WEATHER_API_KEY}")
    private String openWeatherApiKey;
    private static final String OPEN_WEATHER_DOMAIN = "https://api.openweathermap.org/data/3.0/onecall?";
    private static final Integer CACHING_TIMEOUT_SECONDS = 600;
    String ledaLatitude = "35.23649";
    String ledaLongitude = "-85.22971";

    private final ClimbingAreaRepository climbingAreaRepository;

    @Autowired
    public ClimbingAreaServiceImpl(ClimbingAreaRepository climbingAreaRepository){
        this.climbingAreaRepository = climbingAreaRepository;
    }

    @Override
    public ClimbingAreaObj getClimbingAreaData(String areaName) {
        System.out.println(openWeatherApiKey);
        ClimbingAreaEntity area = climbingAreaRepository.findByAreaName(areaName);
        //if(area.getUpdatedAt() == null || Instant.now().getEpochSecond() - area.getUpdatedAt().getEpochSecond() > CACHING_TIMEOUT_SECONDS){
        if(true){
            try{
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder(
                                URI.create(buildApiUrl(area)))
                        .header("Content-Type", "application/json").build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(request);
                System.out.println("OWM Response Code: " + response.statusCode());
            } catch(Exception e){
                System.out.println(e);
            }
        } else {
            System.out.println("else");
        }
        return null;
    }

    private String buildApiUrl(ClimbingAreaEntity area){
        //System.out.println(area.getAreaName());
        //openWeatherApiKey set in env vars
        return String.format("%slat=%s&lon=%s&exclude=minutely&units=imperial&appid=%s",
                OPEN_WEATHER_DOMAIN , ledaLatitude, ledaLongitude, openWeatherApiKey);
    }
}
