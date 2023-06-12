package report.friction.models.weather;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import report.friction.models.ClimbingAreaEntity;

import java.util.Map;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
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
    @ElementCollection
    @CollectionTable(name="current_rain")
    @MapKeyColumn(name="timeframe")
    @Column(name="unit_per_hour")
    @JsonProperty("rain")
    private Map<String, Double> rain;
    @ElementCollection
    @CollectionTable(name="current_snow")
    @MapKeyColumn(name="timeframe")
    @Column(name="unit_per_hour")
    @JsonProperty("snow")
    private Map<String, Double> snow;

}
