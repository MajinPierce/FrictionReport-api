package report.friction.dao;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class HourlyWeather extends Weather{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="area_id")
    private ClimbingAreaEntity climbingArea;

    private Double feelsLike;
    private Double temperature;
}
