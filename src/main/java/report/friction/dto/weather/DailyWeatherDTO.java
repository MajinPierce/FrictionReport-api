package report.friction.dto.weather;

import java.util.List;
import java.util.Map;

public record DailyWeatherDTO(
        Integer dt,
        Integer humidity,
        Double dewPoint,
        Double pop,
        Map<String, Double> temperature,
        List<WeatherDescriptionDTO> weather
){}
