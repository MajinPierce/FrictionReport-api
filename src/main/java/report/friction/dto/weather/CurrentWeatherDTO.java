package report.friction.dto.weather;

public record CurrentWeatherDTO(
        Integer dt,
        Integer humidity,
        Double dewPoint,
        Double temperature
){}
