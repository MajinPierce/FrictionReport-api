package report.friction.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OpenWeatherExceptionResponse {

    public String openWeatherError;
}
