package report.friction.entities.weather;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import report.friction.entities.ClimbingAreaEntity;

import java.util.Map;

//TODO remove list element index wrapper in final api response if possible
// - no need to have that extra layer of complexity when dt is already a property

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
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
    private String summary;
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name="time_of_day")
    @Column(name="temperature")
    @JsonProperty("feels_like")
    private Map<String, Double> feelsLike;
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name="time_of_day")
    @Column(name="temperature")
    @JsonProperty("temp")
    private Map<String, Double> temperature;
    @JsonProperty("pop")
    private Double probabilityOfPrecipitation;

}
