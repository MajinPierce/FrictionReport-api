package report.friction.dao;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class DailyWeather extends Weather{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="area_id")
    private ClimbingAreaEntity climbingArea;

    @OneToOne(mappedBy = "dailyWeather")
    private DailyFeelsLike dailyFeelsLike;

    @OneToOne(mappedBy = "dailyWeather")
    private DailyTemperature dailyTemperature;
}
