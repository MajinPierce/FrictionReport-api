package report.friction.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import report.friction.entities.ClimbingAreaEntity;
import report.friction.entities.weather.CurrentWeather;
import report.friction.entities.weather.DailyWeather;
import report.friction.entities.weather.HourlyWeather;
import report.friction.entities.weather.WeatherDescription;
import report.friction.dto.weather.CurrentWeatherDTO;
import report.friction.dto.weather.DailyWeatherDTO;
import report.friction.dto.weather.HourlyWeatherDTO;
import report.friction.dto.weather.WeatherDescriptionDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClimbingAreaMapper {

    @Mapping(source="areaName", target="name")
    @Mapping(source="currentWeather", target="current")
    @Mapping(source="hourlyWeather", target="hourly")
    @Mapping(source="dailyWeather", target="daily")
    ClimbingAreaDTO climbingAreaEntityToDTO(ClimbingAreaEntity climbingArea);

    CurrentWeatherDTO currentWeatherToDTO(CurrentWeather currentWeather);

    List<HourlyWeatherDTO> hourlyListToDTO(List<HourlyWeather> hourlyWeather);

    HourlyWeatherDTO hourlyWeatherToDTO(HourlyWeather hourlyWeather);

    List<DailyWeatherDTO> dailyListToDailyDTO(List<DailyWeather> dailyWeather);

    @Mapping(source="probabilityOfPrecipitation", target="pop")
    @Mapping(source="weatherDescription", target="weather")
    DailyWeatherDTO dailyWeatherToDTO(DailyWeather dailyWeather);

    List<WeatherDescriptionDTO> weatherDescriptionListToDTO(List<WeatherDescription> weatherDescription);

    WeatherDescriptionDTO weatherDescriptionToDTO(WeatherDescription weatherDescription);

    List<AreaInitDTO> climbingAreaEntityListToAreaInitDTO(List<ClimbingAreaEntity> climbingAreaEntities);

    @Mapping(source="areaName", target="name")
    AreaInitDTO climbingAreaEntityToAreaInitDTO(ClimbingAreaEntity climbingArea);

    List<AreaMapDTO> climbingAreaEntityListToAreaMapDTOList(List<ClimbingAreaEntity> climbingAreaEntities);

    @Mapping(source="areaName", target="name")
    @Mapping(source="currentWeather", target="current")
    AreaMapDTO climbingAreaEntityToAreaMapDTO(ClimbingAreaEntity climbingArea);
}
