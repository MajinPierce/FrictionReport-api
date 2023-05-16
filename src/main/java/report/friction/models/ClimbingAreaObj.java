package report.friction.models;

import lombok.Data;
import report.friction.models.weather.CurrentWeather;
import report.friction.models.weather.DailyWeather;
import report.friction.models.weather.HourlyWeather;

import java.time.Instant;
import java.util.ArrayList;

@Data
public class ClimbingAreaObj {

    private String areaName;
    private Double latitude;
    private Double longitude;
    private Instant updatedAt;
    private CurrentWeather currentWeather;
    private ArrayList<HourlyWeather> hourlyWeather;
    private ArrayList<DailyWeather> dailyWeather;
}
