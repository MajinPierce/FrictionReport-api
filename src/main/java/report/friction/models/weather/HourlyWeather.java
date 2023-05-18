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
public class HourlyWeather extends Weather {

    @JsonProperty("feels_like")
    private Double feelsLike;
    @JsonProperty("temp")
    private Double temperature;
    private Integer visibility;
    @JsonProperty("pop")
    private Integer probabilityOfPrecipitation;
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
    @ManyToOne
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

    public Integer getVisibility() { return visibility; }

    public void setVisibility(Integer visibility) { this.visibility = visibility; }

    public Integer getProbabilityOfPrecipitation() { return probabilityOfPrecipitation; }

    public void setProbabilityOfPrecipitation(Integer probabilityOfPrecipitation) {
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
    }

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
