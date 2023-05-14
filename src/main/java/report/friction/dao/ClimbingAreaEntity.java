package report.friction.dao;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;

@Entity
@Table(name="ClimbingArea")
@NoArgsConstructor
public class ClimbingAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String areaName;
    private Double latitude;
    private Double longitude;
    private Instant updatedAt;
    @OneToOne(mappedBy = "climbingArea")
    private CurrentWeather currentWeather;
    @OneToMany(mappedBy = "climbingArea")
    private ArrayList<HourlyWeather> hourlyWeather;
    @OneToMany(mappedBy = "climbingArea")
    private ArrayList<DailyWeather> dailyWeather;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public ArrayList<HourlyWeather> getHourlyWeather() {
        return hourlyWeather;
    }

    public void setHourlyWeather(ArrayList<HourlyWeather> hourlyWeather) {
        this.hourlyWeather = hourlyWeather;
    }

    public ArrayList<DailyWeather> getDailyWeather() {
        return dailyWeather;
    }

    public void setDailyWeather(ArrayList<DailyWeather> dailyWeather) {
        this.dailyWeather = dailyWeather;
    }
}
