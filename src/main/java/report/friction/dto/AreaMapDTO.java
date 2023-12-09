package report.friction.dto;

import report.friction.dto.weather.CurrentWeatherDTO;

public record AreaMapDTO(
        String name,
        String fullName,
        Double lat,
        Double lon,
        CurrentWeatherDTO currentWeather
){}
