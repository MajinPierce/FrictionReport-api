package report.friction.dao;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

@Entity
@NoArgsConstructor
public class DailyTemperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dailyTemperatureId;

    @OneToOne
    @JoinColumn(name="daily_weather_id")
    private DailyWeather dailyWeather;

    private Double day;
    private Double min;
    private Double max;
    private Double night;
    private Double evening;
    private Double morning;

    public Long getDailyTemperatureId() {
        return dailyTemperatureId;
    }

    public void setDailyTemperatureId(Long dailyTemperatureId) {
        this.dailyTemperatureId = dailyTemperatureId;
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

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
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
