package report.friction.models.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

//FIXME all fields are null
//TODO remove list element index wrapper in final api response if possible

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDescription {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    private Integer id;
    private String main;
    private String description;
    private String icon;
    @ManyToOne
    private Weather weather;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
