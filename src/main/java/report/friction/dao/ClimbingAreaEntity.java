package report.friction.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import report.friction.dao.weather.*;

import java.time.Instant;
import java.util.List;

@Entity
@EqualsAndHashCode(exclude={"currentWeather","hourlyWeather","dailyWeather", "weatherAlerts"})
@Data
@Table(name="climbing_area")
@NoArgsConstructor
public class ClimbingAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    private String areaName;
    @JsonIgnore
    private String fullName;
    @JsonIgnore
    private String mountainProjectUrl;
    @JsonIgnore
    private String state;
    @JsonIgnore
    private Double lat;
    @JsonIgnore
    private Double lon;
    private String timezone;
    @JsonProperty("timezone_offset")
    private Integer timezoneOffset;
    private Instant updatedAt;
    @JsonProperty("current")
    @JsonManagedReference
    @OneToOne(mappedBy="climbingArea", cascade=CascadeType.ALL, orphanRemoval = true)
    private CurrentWeather currentWeather;
    @JsonProperty("hourly")
    @JsonManagedReference
    @OneToMany(mappedBy = "climbingArea", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<HourlyWeather> hourlyWeather;
    @JsonProperty("daily")
    @JsonManagedReference
    @OneToMany(mappedBy = "climbingArea", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<DailyWeather> dailyWeather;
    @JsonProperty("alerts")
    @JsonManagedReference
    @OneToMany(mappedBy = "climbingArea", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<WeatherAlerts> weatherAlerts;

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public void setHourlyWeather(List<HourlyWeather> hourlyWeather) {
        this.hourlyWeather.removeAll(this.hourlyWeather);
        this.hourlyWeather.addAll(hourlyWeather);
    }

    public void setDailyWeather(List<DailyWeather> dailyWeather) {
        this.dailyWeather.removeAll(this.dailyWeather);
        this.dailyWeather.addAll(dailyWeather);
    }

    public void setWeatherAlerts(List<WeatherAlerts> weatherAlerts) {
        this.weatherAlerts.removeAll(this.weatherAlerts);
        this.weatherAlerts.addAll(weatherAlerts);
    }
}
