package report.friction.entities.weather;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import report.friction.entities.ClimbingAreaEntity;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherAlerts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JsonBackReference
    private ClimbingAreaEntity climbingArea;
    @JsonProperty("sender_name")
    private String senderName;
    private String event;
    @JsonProperty("start")
    private Integer startTime;
    @JsonProperty("end")
    private Integer endTime;
    @Column(name="description", length=1800)
    private String description;
    @ElementCollection
    private List<String> tags;

}
