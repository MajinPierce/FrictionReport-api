package report.friction.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

import com.fasterxml.jackson.databind.ObjectMapper;

import report.friction.exceptions.AreaNotFoundException;
import report.friction.exceptions.JacksonMappingException;
import report.friction.exceptions.OpenWeatherException;
import report.friction.models.ClimbingAreaEntity;
import report.friction.repositories.ClimbingAreaRepository;

@Service
public class ClimbingAreaServiceImpl implements ClimbingAreaService{

    @Value("${OPEN_WEATHER_API_KEY}")
    private String openWeatherApiKey;
    private static final String OPEN_WEATHER_DOMAIN = "https://api.openweathermap.org/data/3.0/onecall?";
    private static final Long CACHING_TIMEOUT_SECONDS = 900L;

    private final ClimbingAreaRepository climbingAreaRepository;

    @Autowired
    public ClimbingAreaServiceImpl(ClimbingAreaRepository climbingAreaRepository){
        this.climbingAreaRepository = climbingAreaRepository;
    }

    @Override
    public ClimbingAreaEntity getClimbingAreaData(String areaName) throws AreaNotFoundException{
        try {
            ClimbingAreaEntity area = climbingAreaRepository.findByAreaName(
                    areaName.replaceAll("[-+._:;,^~|]", "").trim().toLowerCase());
            if(area == null){
                throw new AreaNotFoundException("Climbing area not found: " + areaName);
            }
            if(area.getUpdatedAt() == null || (Instant.now().getEpochSecond() - area.getUpdatedAt().getEpochSecond() > CACHING_TIMEOUT_SECONDS)){
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder(
                                    URI.create(buildApiUrl(area)))
                            .header("Content-Type", "application/json").build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.configOverride(ArrayNode.class).setMergeable(false);
                    area = objectMapper.readerForUpdating(area).readValue(response.body());
                    area.onUpdate();
                    return climbingAreaRepository.save(area);
            } else {
                return area;
            }
        } catch (JsonProcessingException e){
            throw new JacksonMappingException("Error mapping OpenWeatherMap Api response to climbing area entity");
        } catch (java.io.IOException | InterruptedException e){
            throw new OpenWeatherException("Error connecting to OpenWeatherMap Api");
        }
    }

    private String buildApiUrl(ClimbingAreaEntity area){
        //openWeatherApiKey set in env vars
        return String.format("%slat=%f&lon=%f&exclude=minutely&units=imperial&appid=%s",
                OPEN_WEATHER_DOMAIN , area.getLat(), area.getLon(), openWeatherApiKey);
    }
}
