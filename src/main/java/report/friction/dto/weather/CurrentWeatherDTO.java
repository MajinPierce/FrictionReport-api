package report.friction.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrentWeatherDTO {

    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("dewPoint")
    private Double dewPoint;
    @JsonProperty("temperature")
    private Double temperature;

}
