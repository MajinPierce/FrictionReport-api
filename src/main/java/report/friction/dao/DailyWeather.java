package report.friction.dao;

import jakarta.persistence.*;

@Entity
public class DailyWeather extends Weather{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="area_id")
    private ClimbingAreaEntity climbingArea;

    @OneToOne
    private DailyFeelsLike dailyFeelsLike;

    @OneToOne
    private DailyTemperature dailyTemperature;
}
