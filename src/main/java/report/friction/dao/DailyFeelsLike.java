package report.friction.dao;

import jakarta.persistence.Entity;
import org.springframework.data.util.Pair;

@Entity
public class DailyFeelsLike {

    private Pair<String, Double> day;
    private Pair<String, Double> night;
    private Pair<String, Double> evening;
    private Pair<String, Double> morning;

}
