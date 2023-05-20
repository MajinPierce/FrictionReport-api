package report.friction.models.weather;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//FIXME entity is either not being created by mapper or not persisted correctly

@Entity
@Data
@NoArgsConstructor
public class WeatherDescription {

    @Id
    private Integer id;
    private String main;
    private String description;
    private String icon;
    @ManyToMany
    private List<Weather> weather = new ArrayList<>();

    public List<Weather> getWeather() { return weather; }

    public void setWeather(List<Weather> weather) {
        if(this.weather == null){
            this.weather = weather;
        } else {
            this.weather.clear();
            this.weather.addAll(weather);
        }
    }
}
