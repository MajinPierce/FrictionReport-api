package report.friction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class OpenWeatherException extends RuntimeException{

    public OpenWeatherException(String message) { super(message); }
}
