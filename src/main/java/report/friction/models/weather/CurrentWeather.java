package report.friction.models.weather;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import report.friction.models.ClimbingAreaEntity;

import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather extends Weather {

    @OneToOne
    @JsonBackReference
    private ClimbingAreaEntity climbingArea;
    @JsonProperty("feels_like")
    private Double feelsLike;
    @JsonProperty("temp")
    private Double temperature;
    private Integer sunrise;
    private Integer sunset;
    private Integer visibility;
    //TODO figure out why nested rain/snow elements throwing error/not mapping correctly
    // - feelsLike property in dailyWeather seems the exact same but it works and this doesn't
//    @ElementCollection
//    @MapKeyColumn(name="timeframe")
//    @Column(name="unit_per_hour")
//    @JsonProperty("rain")
//    private Map<String, Double> rain;
//    @ElementCollection
//    @MapKeyColumn(name="timeframe")
//    @Column(name="unit_per_hour")
//    @JsonProperty("snow")
//    private Map<String, Double> snow;

}
