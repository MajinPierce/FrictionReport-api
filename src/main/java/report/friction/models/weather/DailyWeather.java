package report.friction.models.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import report.friction.models.ClimbingAreaEntity;

import java.util.HashMap;
import java.util.Map;

//TODO remove list element index wrapper in final api response if possible
// - no need to have that extra layer of complexity when dt is already a property

@Entity
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyWeather extends Weather {

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
    //FIXME make climbingArea reference not null in db
    @ManyToOne
    private ClimbingAreaEntity climbingArea;

    public Map<String, Double> getFeelsLike() { return feelsLike; }

    public void setFeelsLike(Map<String, Double> feelsLike) {
        if(this.feelsLike == null){
            this.feelsLike = feelsLike;
        } else {
            this.feelsLike.clear();
            this.feelsLike.putAll(feelsLike);
        }
    }

    public Map<String, Double> getTemperature() { return temperature; }

    public void setTemperature(Map<String, Double> temperature) {
        if(this.temperature == null){
            this.temperature = temperature;
        } else{
            this.temperature.clear();
            this.temperature.putAll(temperature);
        }
    }

    public ClimbingAreaEntity getClimbingArea() { return climbingArea; }

    public void setClimbingArea(ClimbingAreaEntity climbingArea) { this.climbingArea = climbingArea; }
}
