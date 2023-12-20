package report.friction.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import report.friction.entities.weather.*;

import java.time.Instant;
import java.util.List;

@Entity
@EqualsAndHashCode(exclude={"currentWeather","hourlyWeather","dailyWeather", "weatherAlerts"})
@Data
@Table(name="climbing_area")
@NoArgsConstructor
@AllArgsConstructor
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
    @OneToOne(mappedBy="climbingArea", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private CurrentWeather currentWeather;
    @JsonProperty("hourly")
    @JsonManagedReference
    @OneToMany(mappedBy = "climbingArea", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<HourlyWeather> hourlyWeather;
    @JsonProperty("daily")
    @JsonManagedReference
    @OneToMany(mappedBy = "climbingArea", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DailyWeather> dailyWeather;
    @JsonProperty("alerts")
    @JsonManagedReference
    @OneToMany(mappedBy = "climbingArea", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<WeatherAlerts> weatherAlerts;

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public void setHourlyWeather(List<HourlyWeather> hourlyWeather) {
        this.hourlyWeather.clear();
        this.hourlyWeather.addAll(hourlyWeather);
    }

    public void setDailyWeather(List<DailyWeather> dailyWeather) {
        this.dailyWeather.clear();
        this.dailyWeather.addAll(dailyWeather);
    }

    public void setWeatherAlerts(List<WeatherAlerts> weatherAlerts) {
        this.weatherAlerts.clear();
        this.weatherAlerts.addAll(weatherAlerts);
    }
}
