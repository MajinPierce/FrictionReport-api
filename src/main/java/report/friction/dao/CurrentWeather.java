package report.friction.dao;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class CurrentWeather extends Weather{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long currentWeatherId;

    @OneToOne
    @JoinColumn(name="area_id")
    private ClimbingAreaEntity climbingArea;

    private Double feelsLike;
    private Double temperature;

    public Long getCurrentWeatherId() {
        return currentWeatherId;
    }

    public void setCurrentWeatherId(Long currentWeatherId) {
        this.currentWeatherId = currentWeatherId;
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
