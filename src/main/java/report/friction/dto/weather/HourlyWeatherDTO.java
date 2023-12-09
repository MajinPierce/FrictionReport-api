package report.friction.dto.weather;

public record HourlyWeatherDTO(
        Integer dt,
        Integer humidity,
        Double dewPoint,
        Double temperature
){}
