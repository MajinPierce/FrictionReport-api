package report.friction.models.weather;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class WeatherDescription {

    @Id
    private Integer id;
    private String main;
    private String description;
    private String icon;
    @ManyToMany
    private List<Weather> weather = new ArrayList<>();

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getMain() { return main; }

    public void setMain(String main) { this.main = main; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getIcon() { return icon; }

    public void setIcon(String icon) { this.icon = icon; }

    public List<Weather> getWeather() { return weather; }

    public void setWeather(List<Weather> weather) {
        if(!this.weather.isEmpty()){
            this.weather.clear();
        }
        this.weather.addAll(weather);
    }
}
