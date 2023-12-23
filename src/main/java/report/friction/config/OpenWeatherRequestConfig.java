package report.friction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class OpenWeatherRequestConfig {

    public static final Long CACHING_TIMEOUT_SECONDS = 900L;

    @Bean
    HttpClient httpClient(){
        return HttpClient.newHttpClient();
    }

}
