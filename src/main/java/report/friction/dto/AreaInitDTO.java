package report.friction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AreaInitDTO {

    @JsonProperty("name")
    private String name;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("state")
    private String state;

}
