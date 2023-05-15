package report.friction.dao;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class DailyFeelsLike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dailyFeelsLikeId;

    @OneToOne
    @JoinColumn(name="daily_weather_id")
    private DailyWeather dailyWeather;

    private Double day;
    private Double night;
    private Double evening;
    private Double morning;

    public Long getDailyFeelsLikeId() {
        return dailyFeelsLikeId;
    }

    public void setDailyFeelsLikeId(Long dailyFeelsLikeId) {
        this.dailyFeelsLikeId = dailyFeelsLikeId;
    }

    public DailyWeather getDailyWeather() {
        return dailyWeather;
    }

    public void setDailyWeather(DailyWeather dailyWeather) {
        this.dailyWeather = dailyWeather;
    }

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getNight() {
        return night;
    }

    public void setNight(Double night) {
        this.night = night;
    }

    public Double getEvening() {
        return evening;
    }

    public void setEvening(Double evening) {
        this.evening = evening;
    }

    public Double getMorning() {
        return morning;
    }

    public void setMorning(Double morning) {
        this.morning = morning;
    }
}
