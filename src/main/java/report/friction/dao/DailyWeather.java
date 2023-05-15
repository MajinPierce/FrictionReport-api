package report.friction.dao;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class DailyWeather extends Weather{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dailyWeatherId;

    @ManyToOne
    @JoinColumn(name="area_id")
    private ClimbingAreaEntity climbingArea;

    @OneToOne(mappedBy = "dailyWeather")
    private DailyFeelsLike dailyFeelsLike;

    @OneToOne(mappedBy = "dailyWeather")
    private DailyTemperature dailyTemperature;

    public Long getDailyWeatherId() {
        return dailyWeatherId;
    }

    public void setDailyWeatherId(Long dailyWeatherId) {
        this.dailyWeatherId = dailyWeatherId;
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
