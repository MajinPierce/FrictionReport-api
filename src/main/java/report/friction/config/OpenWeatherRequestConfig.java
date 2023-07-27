package report.friction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class OpenWeatherRequestConfig {

    public static String domain;
    public static String apiKey;
    public static String units;

    @Value("${openweather.domain}")
    private void setDomain(String domain) {
        OpenWeatherRequestConfig.domain = domain;
    }

    @Value("${openweather.apiKey}")
    private void setApiKey(String apiKey) {
        OpenWeatherRequestConfig.apiKey = apiKey;
    }

    @Value("${openweather.units}")
    private void setUnits(String units) {
        OpenWeatherRequestConfig.units = units;
    }

    @Bean
    HttpClient httpClient(){
        return HttpClient.newHttpClient();
    }

}
