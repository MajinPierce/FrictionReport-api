package report.friction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import report.friction.dao.ClimbingAreaRepository;
import report.friction.models.ClimbingAreaObj;

@Service
public class ClimbingAreaServiceImpl implements ClimbingAreaService{

    @Value("${OPEN_WEATHER_API_KEY}")
    private String openWeatherApiKey;

    private static final String OPEN_WEATHER_DOMAIN = "https://api.openweathermap.org/data/3.0/onecall?";

    private final ClimbingAreaRepository climbingAreaRepository;

    @Autowired
    public ClimbingAreaServiceImpl(ClimbingAreaRepository climbingAreaRepository){
        this.climbingAreaRepository = climbingAreaRepository;
    }

    @Override
    public ClimbingAreaObj getClimbingAreaData(String areaName) {
        System.out.println(openWeatherApiKey);
        return null;
    }
}
