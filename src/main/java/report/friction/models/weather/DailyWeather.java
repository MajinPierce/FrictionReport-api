package report.friction.models.weather;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import report.friction.models.ClimbingAreaEntity;

import java.util.Map;

@Entity
@NoArgsConstructor
public class DailyWeather extends Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection
    @MapKeyColumn(name="time_of_day")
    @Column(name="temperature")
    private Map<String, Double> feelsLike;
    @ElementCollection
    @MapKeyColumn(name="time_of_day")
    @Column(name="temperature")
    private Map<String, Double> temperature;
    @ManyToOne
    private ClimbingAreaEntity climbingArea;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Double> getFeelsLike() { return feelsLike; }

    public void setFeelsLike(Map<String, Double> feelsLike) { this.feelsLike = feelsLike; }

    public Map<String, Double> getTemperature() { return temperature; }

    public void setTemperature(Map<String, Double> temperature) { this.temperature = temperature; }

    public ClimbingAreaEntity getClimbingArea() { return climbingArea; }

    public void setClimbingArea(ClimbingAreaEntity climbingArea) { this.climbingArea = climbingArea; }
}
