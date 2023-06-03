package report.friction.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import report.friction.models.weather.CurrentWeather;
import report.friction.models.weather.DailyWeather;
import report.friction.models.weather.HourlyWeather;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@Table(name="climbing_area")
@NoArgsConstructor
public class ClimbingAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private String areaName;
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
    //TODO add weather alerts

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
}
