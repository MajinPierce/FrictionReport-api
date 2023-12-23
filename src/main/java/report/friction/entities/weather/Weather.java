package report.friction.entities.weather;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;


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
    @JsonManagedReference
    @OneToMany(mappedBy="weather", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WeatherDescription> weatherDescription;

}
