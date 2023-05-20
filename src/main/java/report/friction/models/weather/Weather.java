package report.friction.models.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

    public List<WeatherDescription> getWeatherDescription() {
        return weatherDescription;
    }

    //TODO test if weather description setter method is why it's not being populated
    // - except daily weather setTemperature seems to work (though a map, not a list)
    public void setWeatherDescription(List<WeatherDescription> weatherDescription) {
        if(this.weatherDescription == null){
            this.weatherDescription = weatherDescription;
        } else {
            this.weatherDescription.clear();
            this.weatherDescription.addAll(weatherDescription);
        }
    }
}
