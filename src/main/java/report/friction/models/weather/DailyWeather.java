package report.friction.models.weather;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import report.friction.models.ClimbingAreaEntity;

import java.util.HashMap;
import java.util.Map;

//TODO remove list element index wrapper in final api response if possible
// - no need to have that extra layer of complexity when dt is already a property

@Entity
@Data
@NoArgsConstructor
public class DailyWeather extends Weather {

    @ManyToOne
    @JsonBackReference
    private ClimbingAreaEntity climbingArea;
    private Integer sunrise;
    private Integer sunset;
    private Integer moonrise;
    private Integer moonset;
    @JsonProperty("moon_phase")
    private Double moonPhase;
    private Double rain;
    private Double snow;
    @ElementCollection
    @MapKeyColumn(name="time_of_day")
    @Column(name="temperature")
    @JsonProperty("feels_like")
    private Map<String, Double> feelsLike;
    @ElementCollection
    @MapKeyColumn(name="time_of_day")
    @Column(name="temperature")
    @JsonProperty("temp")
    private Map<String, Double> temperature;
    @JsonProperty("pop")
    private Integer probabilityOfPrecipitation;

}
