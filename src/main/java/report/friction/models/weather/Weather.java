package report.friction.models.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer dt;
    private Integer humidity;
    @JsonProperty("dew_point")
    private Double dewPoint;
    private Integer pressure;
    @JsonProperty("wind_speed")
    private Double windSpeed;
    @JsonProperty("wind_deg")
    private Integer windDegree;
    @JsonProperty("wind_gust")
    private Double windGust;
    @JsonProperty("clouds")
    private Integer cloudCover;
    private Double uvi;
    @JsonProperty("weather")
    @ManyToMany(mappedBy="weather")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<WeatherDescription> weatherDescription = new ArrayList<>();

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Integer getPressure() { return pressure; }

    public void setPressure(Integer pressure) { this.pressure = pressure; }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindDegree() { return windDegree; }

    public void setWindDegree(Integer windDegree) { this.windDegree = windDegree; }

    public Double getWindGust() { return windGust; }

    public void setWindGust(Double windGust) { this.windGust = windGust; }

    public Integer getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Integer cloudCover) { this.cloudCover = cloudCover; }

    public Double getUvi() {
        return uvi;
    }

    public void setUvi(Double uvi) {
        this.uvi = uvi;
    }

    public List<WeatherDescription> getWeatherDescription() {
        return weatherDescription;
    }

    //TODO test if weather description setter method is why it's not being populated
    // - except daily weather setTemperature seems to work (though a map, not a list)
    public void setWeatherDescription(List<WeatherDescription> weatherDescription) {
        if(!this.weatherDescription.isEmpty()) {
            this.weatherDescription.clear();
        }
        this.weatherDescription.addAll(weatherDescription);
    }
}
