package report.friction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherDescriptionDTO {

    @JsonProperty("icon")
    private String icon;
}
