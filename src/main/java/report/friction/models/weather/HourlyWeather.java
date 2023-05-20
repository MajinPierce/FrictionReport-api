package report.friction.models.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import report.friction.models.ClimbingAreaEntity;

import java.util.Map;

//TODO remove list element index wrapper in final api response if possible
// - no need to have that extra layer of complexity when dt is already a property

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HourlyWeather extends Weather {

    @JsonProperty("feels_like")
    private Double feelsLike;
    @JsonProperty("temp")
    private Double temperature;
    private Integer visibility;
    @JsonProperty("pop")
    private Integer probabilityOfPrecipitation;
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
    //FIXME make climbingArea reference not null in db
    @ManyToOne
    private ClimbingAreaEntity climbingArea;

//    public Map<String, Double> getRain() { return rain; }
//
//    public void setRain(Map<String, Double> rain) { this.rain = rain; }
//
//    public Map<String, Double> getSnow() { return snow; }
//
//    public void setSnow(Map<String, Double> snow) { this.snow = snow; }

    public ClimbingAreaEntity getClimbingArea() { return climbingArea; }

    public void setClimbingArea(ClimbingAreaEntity climbingArea) { this.climbingArea = climbingArea; }
}
