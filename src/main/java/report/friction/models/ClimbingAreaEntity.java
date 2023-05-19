package report.friction.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import report.friction.models.weather.CurrentWeather;
import report.friction.models.weather.DailyWeather;
import report.friction.models.weather.HourlyWeather;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="ClimbingArea")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
    //FIXME updatedAt value not being set
    // - preUpdate vs postUpdate?
    private Instant updatedAt;
    //FIXME entity relationships not setting climbing area foreign key id
    @JsonProperty("current")
    @OneToOne(mappedBy="climbingArea", cascade=CascadeType.ALL)
    private CurrentWeather currentWeather;
    @JsonProperty("hourly")
    @OneToMany(mappedBy = "climbingArea", cascade=CascadeType.ALL)
    private List<HourlyWeather> hourlyWeather = new ArrayList<>();
    @JsonProperty("daily")
    @OneToMany(mappedBy = "climbingArea", cascade=CascadeType.ALL)
    private List<DailyWeather> dailyWeather = new ArrayList<>();

    @PostUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getTimezone() { return timezone; }

    public void setTimezone(String timezone) { this.timezone = timezone; }

    public Integer getTimezoneOffset() { return timezoneOffset; }

    public void setTimezoneOffset(Integer timezoneOffset) { this.timezoneOffset = timezoneOffset; }

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
