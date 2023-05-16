package report.friction.models.weather;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import report.friction.models.ClimbingAreaEntity;

@Entity
@NoArgsConstructor
public class HourlyWeather extends Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double feelsLike;
    private Double temperature;
    @ManyToOne
    private ClimbingAreaEntity climbingArea;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ClimbingAreaEntity getClimbingArea() { return climbingArea; }

    public void setClimbingArea(ClimbingAreaEntity climbingArea) { this.climbingArea = climbingArea; }
}
