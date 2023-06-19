package report.friction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import report.friction.dto.weather.CurrentWeatherDTO;

@Data
public class AreaMapDTO {

    @JsonProperty("name")
    private String name;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("latitude")
    private Double lat;
    @JsonProperty("longitude")
    private Double lon;
    @JsonProperty("current")
    private CurrentWeatherDTO currentWeather;

}
