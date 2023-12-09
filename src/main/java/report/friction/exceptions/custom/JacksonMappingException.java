package report.friction.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class JacksonMappingException extends RuntimeException{

    public JacksonMappingException(String message) {super(message);}
}
