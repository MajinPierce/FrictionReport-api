package report.friction.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import report.friction.dto.AreaInitDTO;
import report.friction.dto.ClimbingAreaDTO;
import report.friction.dto.ClimbingAreaMapper;
import report.friction.dto.ClimbingAreaMapperImpl;
import report.friction.exceptions.AreaNotFoundException;
import report.friction.exceptions.JacksonMappingException;
import report.friction.exceptions.OpenWeatherException;
import report.friction.dao.ClimbingAreaEntity;
import report.friction.repositories.ClimbingAreaRepository;

@Service
public class ClimbingAreaServiceImpl implements ClimbingAreaService{

    @Value("${OPEN_WEATHER_API_KEY}")
    private String OPEN_WEATHER_API_KEY;
    private static final String OPEN_WEATHER_DOMAIN = "https://api.openweathermap.org/data/3.0/onecall?";
    private static final Long CACHING_TIMEOUT_SECONDS = (Long) 900L;

    private final ClimbingAreaRepository climbingAreaRepository;
    private final ClimbingAreaMapper climbingAreaMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ClimbingAreaServiceImpl(ClimbingAreaRepository climbingAreaRepository, ClimbingAreaMapperImpl climbingAreaMapper){
        this.climbingAreaRepository = climbingAreaRepository;
        this.climbingAreaMapper = climbingAreaMapper;
    }

    public List<AreaInitDTO> getAreasInit(){
        return climbingAreaMapper.climbingAreaEntityListToAreaInitDTOList(climbingAreaRepository.findAll());
    }

    @Override
    public ClimbingAreaDTO getClimbingAreaData(String areaName)
            throws AreaNotFoundException, JacksonMappingException, OpenWeatherException{
        try {
            ClimbingAreaEntity area = climbingAreaRepository.findByAreaName(
                    areaName.replaceAll("[-+._]", "").trim().toLowerCase());
            if(area == null){
                throw new AreaNotFoundException("Climbing area not found: " + areaName);
            }
            if(area.getUpdatedAt() == null || (Instant.now().getEpochSecond() - area.getUpdatedAt().getEpochSecond() > CACHING_TIMEOUT_SECONDS)){
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder(
                                URI.create(buildApiUrl(area)))
                        .header("Content-Type", "application/json").build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                area = objectMapper.readerForUpdating(area).readValue(response.body());
                area.onUpdate();
                return climbingAreaMapper.climbingAreaEntityToClimbingAreaDTO(climbingAreaRepository.save(area));
            } else {
                return climbingAreaMapper.climbingAreaEntityToClimbingAreaDTO(area);
            }
        } catch (JsonProcessingException e){
            throw new JacksonMappingException("Error mapping OpenWeatherMap Api response to climbing area entity");
        } catch (java.io.IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            throw new OpenWeatherException("Error connecting to OpenWeatherMap Api");
        }
    }

    private String buildApiUrl(ClimbingAreaEntity area){
        //openWeatherApiKey set in env vars
        return String.format("%slat=%f&lon=%f&exclude=minutely&units=imperial&appid=%s",
                OPEN_WEATHER_DOMAIN , area.getLat(), area.getLon(), OPEN_WEATHER_API_KEY);
    }

}
