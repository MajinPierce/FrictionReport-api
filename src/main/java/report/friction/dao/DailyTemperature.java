package report.friction.dao;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

@Entity
@NoArgsConstructor
public class DailyTemperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="day_id")
    private DailyWeather dailyWeather;

    private Double day;
    private Double min;
    private Double max;
    private Double night;
    private Double evening;
    private Double morning;
}
