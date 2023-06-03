package report.friction.models.weather;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO remove list element index wrapper in final api response if possible

@Entity
@Data
@NoArgsConstructor
public class WeatherDescription {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    private Integer id;
    private String main;
    private String description;
    private String icon;
    @ManyToOne
    @JsonBackReference
    private Weather weather;

}
