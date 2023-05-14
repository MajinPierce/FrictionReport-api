package report.friction.dao;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class DailyWeather extends Weather{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="area_id")
    private ClimbingAreaEntity climbingArea;

    @OneToOne(mappedBy = "dailyWeather")
    private DailyFeelsLike dailyFeelsLike;

    @OneToOne(mappedBy = "dailyWeather")
    private DailyTemperature dailyTemperature;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClimbingAreaEntity getClimbingArea() {
        return climbingArea;
    }

    public void setClimbingArea(ClimbingAreaEntity climbingArea) {
        this.climbingArea = climbingArea;
    }

    public DailyFeelsLike getDailyFeelsLike() {
        return dailyFeelsLike;
    }

    public void setDailyFeelsLike(DailyFeelsLike dailyFeelsLike) {
        this.dailyFeelsLike = dailyFeelsLike;
    }

    public DailyTemperature getDailyTemperature() {
        return dailyTemperature;
    }

    public void setDailyTemperature(DailyTemperature dailyTemperature) {
        this.dailyTemperature = dailyTemperature;
    }
}
