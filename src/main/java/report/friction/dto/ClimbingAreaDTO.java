package report.friction.dto;

import report.friction.dto.weather.CurrentWeatherDTO;
import report.friction.dto.weather.DailyWeatherDTO;
import report.friction.dto.weather.HourlyWeatherDTO;

import java.util.List;

public record ClimbingAreaDTO(
        String name,
        String fullName,
        Double lat,
        Double lon,
        String mountainProjectUrl,
        String state,
        CurrentWeatherDTO current,
        List<HourlyWeatherDTO> hourly,
        List<DailyWeatherDTO> daily
){}
