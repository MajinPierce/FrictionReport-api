package report.friction.models.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import report.friction.models.ClimbingAreaEntity;

import java.util.Map;

@Entity
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather extends Weather {

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
    //FIXME make climbingArea reference not null in db
    @OneToOne
    private ClimbingAreaEntity climbingArea;

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getSunrise() { return sunrise; }

    public void setSunrise(Integer sunrise) { this.sunrise = sunrise; }

    public Integer getSunset() { return sunset; }

    public void setSunset(Integer sunset) { this.sunset = sunset; }

    public Integer getVisibility() { return visibility; }

    public void setVisibility(Integer visibility) { this.visibility = visibility; }

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
