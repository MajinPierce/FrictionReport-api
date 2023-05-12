package report.friction.dao;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class DailyFeelsLike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="day_id")
    private DailyWeather dailyWeather;

    private Double day;
    private Double night;
    private Double evening;
    private Double morning;

}
