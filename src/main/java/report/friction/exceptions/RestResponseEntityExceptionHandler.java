package report.friction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<AreaNotFoundExceptionResponse> handleAreaNotFoundException(AreaNotFoundException e){
        AreaNotFoundExceptionResponse exceptionResponse = new AreaNotFoundExceptionResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<OpenWeatherExceptionResponse> handleOpenWeatherException(OpenWeatherException e){
        OpenWeatherExceptionResponse exceptionResponse = new OpenWeatherExceptionResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse,  HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler
    public final ResponseEntity<JacksonMappingExceptionResponse> handleJacksonMappingException(JacksonMappingException e){
        JacksonMappingExceptionResponse exceptionResponse = new JacksonMappingExceptionResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse,  HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
