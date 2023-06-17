package report.friction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ClimbingAreaDTO {

    @JsonProperty("name")
    private String name;
    @JsonProperty("latitude")
    private Double lat;
    @JsonProperty("longitude")
    private Double lon;
    @JsonProperty("mountainProjectUrl")
    private String mountainProjectUrl;
    @JsonProperty("state")
    private String state;
    @JsonProperty("current")
    private CurrentWeatherDTO currentWeather;
    @JsonProperty("hourly")
    private List<HourlyWeatherDTO> hourlyWeather;
    @JsonProperty("daily")
    private List<DailyWeatherDTO> dailyWeather;
}
