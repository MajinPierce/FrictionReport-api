package report.friction.dao;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class HourlyWeather extends Weather{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hourlyWeatherId;

    @ManyToOne
    @JoinColumn(name="area_id")
    private ClimbingAreaEntity climbingArea;

    private Double feelsLike;
    private Double temperature;

    public Long getHourlyWeatherId() {
        return hourlyWeatherId;
    }

    public void setHourlyWeatherId(Long hourlyWeatherId) {
        this.hourlyWeatherId = hourlyWeatherId;
    }

    public ClimbingAreaEntity getClimbingArea() {
        return climbingArea;
    }

    public void setClimbingArea(ClimbingAreaEntity climbingArea) {
        this.climbingArea = climbingArea;
    }

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
}
