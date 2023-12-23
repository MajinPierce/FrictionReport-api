package report.friction.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.util.UriComponentsBuilder;
import report.friction.dto.*;
import report.friction.models.Exclude;
import report.friction.exceptions.custom.AreaNotFoundException;
import report.friction.exceptions.custom.JacksonMappingException;
import report.friction.exceptions.custom.OpenWeatherException;
import report.friction.entities.ClimbingAreaEntity;
import report.friction.repositories.ClimbingAreaRepository;

import static report.friction.config.OpenWeatherRequestConfig.*;

@Slf4j
@Service
public class ClimbingAreaServiceImpl implements ClimbingAreaService {

    private final ClimbingAreaRepository climbingAreaRepository;
    private final ClimbingAreaMapper climbingAreaMapper;
    private final HttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String ONE_CALL;
    private String API_KEY;
    private String UNITS;

    public ClimbingAreaServiceImpl(ClimbingAreaRepository climbingAreaRepository,
                                   ClimbingAreaMapper climbingAreaMapper,
                                   HttpClient client,
                                   @Value("${openweather.domain}") String onecall,
                                   @Value("${openweather.apiKey}") String apiKey,
                                   @Value("${openweather.units}") String units){
        this.climbingAreaRepository = climbingAreaRepository;
        this.climbingAreaMapper = climbingAreaMapper;
        this.client = client;
        ONE_CALL = onecall;
        API_KEY = apiKey;
        UNITS = units;
    }

    public List<AreaInitDTO> getAreasInit(){
        log.debug("Getting area init data");
        return climbingAreaMapper.climbingAreaEntityListToAreaInitDTO(climbingAreaRepository.findAll());
    }

    public List<AreaMapDTO> getAreaMapData(){
        log.debug("Getting area map data");
        List<ClimbingAreaEntity> allAreas = climbingAreaRepository.findAll();
        List<ClimbingAreaEntity> areasToUpdate = allAreas.stream().parallel()
                .filter(area -> (area.getUpdatedAt() == null ||
                        (Instant.now().getEpochSecond() - area.getUpdatedAt().getEpochSecond() > CACHING_TIMEOUT_SECONDS)))
                .map(this::updateAreaData)
                .toList();
        climbingAreaRepository.saveAll(areasToUpdate);
        climbingAreaRepository.flush();
        return climbingAreaMapper.climbingAreaEntityListToAreaMapDTOList(allAreas);
    }

    public ClimbingAreaDTO getClimbingAreaData(String areaName){
        log.debug("Getting area weather data: " + areaName);
        Optional<ClimbingAreaEntity> areaOpt = climbingAreaRepository.findByAreaName(
                areaName.replaceAll("[-+._ ]", "").trim().toLowerCase()
        );
        if(areaOpt.isEmpty()){
            log.warn("Tried accessing area that doesn't exist: {}", areaName);
            throw new AreaNotFoundException("Climbing area not found: " + areaName);
        }
        ClimbingAreaEntity area = areaOpt.get();
        if(area.getUpdatedAt() == null || (Instant.now().getEpochSecond() - area.getUpdatedAt().getEpochSecond() > CACHING_TIMEOUT_SECONDS)){
            updateAreaData(area);
            return climbingAreaMapper.climbingAreaEntityToDTO(climbingAreaRepository.save(area));
        } else {
            return climbingAreaMapper.climbingAreaEntityToDTO(area);
        }
    }

    private ClimbingAreaEntity updateAreaData(ClimbingAreaEntity area){
        log.debug("Updating data for area: {}", area.getFullName());
        try {
            HttpRequest request = HttpRequest
                    .newBuilder().GET().uri(
                            UriComponentsBuilder.fromHttpUrl(ONE_CALL)
                                    .queryParam("lat", area.getLat())
                                    .queryParam("lon", area.getLon())
                                    .queryParam("exclude", Exclude.MINUTELY.value)
                                    .queryParam("units", UNITS)
                                    .queryParam("appid", API_KEY)
                                    .build()
                                    .toUri()
                    )
                    .header("Content-Type", "application/json").build();
            log.debug("Sending open weather map request to url: {}",
                    request.uri().toString().replaceAll("(?<=&appid=).*", "OPEN_WEATHER_API_KEY")
            );
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            area = objectMapper.readerForUpdating(area).readValue(response.body());
            area.onUpdate();
            return area;
        } catch (JsonProcessingException e){
            String errorMessage = "Error mapping OpenWeatherMap Api response to climbing area entity";
            log.error(errorMessage, e);
            throw new JacksonMappingException(errorMessage);
        } catch (java.io.IOException | InterruptedException e){
            String errorMessage = "Error connecting to OpenWeatherMap Api";
            log.error(errorMessage, e);
            throw new OpenWeatherException(errorMessage);
        }
    }
}
