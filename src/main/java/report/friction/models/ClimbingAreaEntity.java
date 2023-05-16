package report.friction.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import report.friction.models.weather.CurrentWeather;
import report.friction.models.weather.DailyWeather;
import report.friction.models.weather.HourlyWeather;

import java.time.Instant;
import java.util.List;

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
    @OneToOne(mappedBy="climbingArea", cascade=CascadeType.ALL, orphanRemoval=true)
    private CurrentWeather currentWeather;
    @OneToMany(mappedBy = "climbingArea", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<HourlyWeather> hourlyWeather;
    @OneToMany(mappedBy = "climbingArea", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<DailyWeather> dailyWeather;

    @PostUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public List<HourlyWeather> getHourlyWeather() {
        return hourlyWeather;
    }

    public void setHourlyWeather(List<HourlyWeather> hourlyWeather) {
        this.hourlyWeather = hourlyWeather;
    }

    public List<DailyWeather> getDailyWeather() {
        return dailyWeather;
    }

    public void setDailyWeather(List<DailyWeather> dailyWeather) {
        this.dailyWeather = dailyWeather;
    }
}
