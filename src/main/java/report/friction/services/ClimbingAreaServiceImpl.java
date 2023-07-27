package report.friction.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
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

import report.friction.dto.*;
import report.friction.entities.OpenWeatherRequest;
import report.friction.exceptions.AreaNotFoundException;
import report.friction.exceptions.JacksonMappingException;
import report.friction.exceptions.OpenWeatherException;
import report.friction.entities.ClimbingAreaEntity;
import report.friction.repositories.ClimbingAreaRepository;

@Slf4j
@Service
public class ClimbingAreaServiceImpl implements ClimbingAreaService{

    @Value("${OPEN_WEATHER_API_KEY}")
    private String OPEN_WEATHER_API_KEY;
    private static final String OPEN_WEATHER_DOMAIN = "https://api.openweathermap.org/data/3.0/onecall?";
    private static final Long CACHING_TIMEOUT_SECONDS = 900L;

    private final ClimbingAreaRepository climbingAreaRepository;
    private final ClimbingAreaMapper climbingAreaMapper;
    private final HttpClient client;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ClimbingAreaServiceImpl(ClimbingAreaRepository climbingAreaRepository, ClimbingAreaMapperImpl climbingAreaMapper, HttpClient client){
        this.climbingAreaRepository = climbingAreaRepository;
        this.climbingAreaMapper = climbingAreaMapper;
        this.client = client;
    }

    public List<AreaInitDTO> getAreasInit(){
        log.debug("Getting area init data");
        return climbingAreaMapper.climbingAreaEntityListToAreaInitDTOList(climbingAreaRepository.findAll());
    }

    public List<AreaMapDTO> getAreaMapData(){
        log.debug("Getting area map data");
        List<ClimbingAreaEntity> allAreas = climbingAreaRepository.findAll();
        allAreas.stream().filter(area -> (
                area.getUpdatedAt() == null ||
                        (Instant.now().getEpochSecond() - area.getUpdatedAt().getEpochSecond() > CACHING_TIMEOUT_SECONDS)))
                .forEach(area -> updateAreaData(area));
        return climbingAreaMapper.climbingAreaEntityListToAreaMapDTOList(allAreas);
    }

    @Override
    public ClimbingAreaDTO getClimbingAreaData(String areaName)
            throws AreaNotFoundException, JacksonMappingException, OpenWeatherException{
        try {
            ClimbingAreaEntity area = climbingAreaRepository.findByAreaName(
                    areaName.replaceAll("[-+._ ]", "").trim().toLowerCase());
            if(area == null){
                log.error("Tried accessing area that doesn't exist: {}", areaName);
                throw new AreaNotFoundException("Climbing area not found: " + areaName);
            }
            if(area.getUpdatedAt() == null || (Instant.now().getEpochSecond() - area.getUpdatedAt().getEpochSecond() > CACHING_TIMEOUT_SECONDS)){
                HttpRequest request = HttpRequest.newBuilder(
                                URI.create(OpenWeatherRequest.newRequest(area).buildString()))
                        .header("Content-Type", "application/json").build();
                log.info("Sending open weather map request to url: {}", request.uri().toString().replaceAll("(?<=&appid=).*", "OPEN_WEATHER_API_KEY"));
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                area = objectMapper.readerForUpdating(area).readValue(response.body());
                area.onUpdate();
                log.debug("Updating data for area: {}", area.getFullName());
                return climbingAreaMapper.climbingAreaEntityToClimbingAreaDTO(climbingAreaRepository.save(area));
            } else {
                return climbingAreaMapper.climbingAreaEntityToClimbingAreaDTO(area);
            }
        } catch (JsonProcessingException e){
            log.error(String.valueOf(e));
            throw new JacksonMappingException("Error mapping OpenWeatherMap Api response to climbing area entity");
        } catch (java.io.IOException | InterruptedException e){
            log.error(String.valueOf(e));
            Thread.currentThread().interrupt();
            throw new OpenWeatherException("Error connecting to OpenWeatherMap Api");
        }
    }

    private String buildApiUrl(ClimbingAreaEntity area){
        //openWeatherApiKey set in env vars
        log.debug("Creating open weather map api url for {}", area.getFullName());
        return String.format("%slat=%f&lon=%f&exclude=minutely&units=imperial&appid=%s",
                OPEN_WEATHER_DOMAIN , area.getLat(), area.getLon(), OPEN_WEATHER_API_KEY);
    }

    private void updateAreaData(ClimbingAreaEntity area){
        try {
            HttpRequest request = HttpRequest.newBuilder(
                            URI.create(OpenWeatherRequest.newRequest(area).buildString()))
                    .header("Content-Type", "application/json").build();
            log.info("Sending open weather map request to url: {}", request.uri().toString().replaceAll("(?<=&appid=).*", "OPEN_WEATHER_API_KEY"));
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            area = objectMapper.readerForUpdating(area).readValue(response.body());
            area.onUpdate();
            log.debug("Updating data for area: {}", area.getFullName());
            climbingAreaRepository.save(area);
        } catch (JsonProcessingException e){
            log.error(String.valueOf(e));
            throw new JacksonMappingException("Error mapping OpenWeatherMap Api response to climbing area entity");
        } catch (java.io.IOException | InterruptedException e){
            log.error(String.valueOf(e));
            Thread.currentThread().interrupt();
            throw new OpenWeatherException("Error connecting to OpenWeatherMap Api");
        }
    }

}
