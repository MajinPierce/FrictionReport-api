package report.friction.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import report.friction.dao.ClimbingAreaEntity;
import report.friction.dao.weather.CurrentWeather;
import report.friction.dao.weather.DailyWeather;
import report.friction.dao.weather.HourlyWeather;
import report.friction.dao.weather.WeatherDescription;
import report.friction.dto.weather.CurrentWeatherDTO;
import report.friction.dto.weather.DailyWeatherDTO;
import report.friction.dto.weather.HourlyWeatherDTO;
import report.friction.dto.weather.WeatherDescriptionDTO;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ClimbingAreaMapper {

    @Mapping(source="areaName", target="name")
    ClimbingAreaDTO climbingAreaEntityToClimbingAreaDTO(ClimbingAreaEntity climbingArea);

    CurrentWeatherDTO currentWeatherToCurrentWeatherDTO(CurrentWeather currentWeather);

    List<HourlyWeatherDTO> hourlyListToHourlyDTOList(List<HourlyWeather> hourlyWeather);

    HourlyWeatherDTO hourlyWeatherToHourlyWeatherDTO(HourlyWeather hourlyWeather);

    List<DailyWeatherDTO> dailyListToDailyDTOList(List<DailyWeather> dailyWeather);

    @Mapping(source="probabilityOfPrecipitation", target="pop")
    DailyWeatherDTO dailyWeatherToDailyWeatherDTO(DailyWeather dailyWeather);

    List<WeatherDescriptionDTO> weatherDescriptionToWeatherDescriptionDTOList(List<WeatherDescription> weatherDescription);

    WeatherDescriptionDTO weatherDescriptionToWeatherDescriptionDTO(WeatherDescription weatherDescription);

    List<AreaInitDTO> climbingAreaEntityListToAreaInitDTOList(List<ClimbingAreaEntity> climbingAreaEntities);

    @Mapping(source="areaName", target="name")
    AreaInitDTO climbingAreaEntityToAreaInitDTO(ClimbingAreaEntity climbingArea);
}
