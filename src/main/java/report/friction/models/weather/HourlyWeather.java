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

//TODO remove list element index wrapper in final api response if possible
// - no need to have that extra layer of complexity when dt is already a property

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HourlyWeather extends Weather {

    @ManyToOne
    @JsonBackReference
    private ClimbingAreaEntity climbingArea;
    @JsonProperty("feels_like")
    private Double feelsLike;
    @JsonProperty("temp")
    private Double temperature;
    private Integer visibility;
    @JsonProperty("pop")
    private Integer probabilityOfPrecipitation;
    //there was an error with hibernate mapping and ElementCollection
    //updating from 6.1.7 -> 6.2.4 fixed
    @ElementCollection
    @MapKeyColumn(name="timeframe")
    @Column(name="unit_per_hour")
    @JsonProperty("rain")
    private Map<String, Double> rain;
    @ElementCollection
    @MapKeyColumn(name="timeframe")
    @Column(name="unit_per_hour")
    @JsonProperty("snow")
    private Map<String, Double> snow;

}
