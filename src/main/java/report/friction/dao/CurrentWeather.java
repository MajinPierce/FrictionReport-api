package report.friction.dao;

import jakarta.persistence.*;

@Entity
public class CurrentWeather extends Weather{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="area_id")
    private ClimbingAreaEntity climbingArea;

    private Double feelsLike;
    private Double temperature;

}
