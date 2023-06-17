package report.friction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DailyWeatherDTO {

    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("dewPoint")
    private Double dewPoint;
    @JsonProperty("pop")
    private Double pop;
    @JsonProperty("temperature")
    private Map<String, Double> temperature;
    @JsonProperty("weather")
    private List<WeatherDescriptionDTO> weatherDescription;
}
