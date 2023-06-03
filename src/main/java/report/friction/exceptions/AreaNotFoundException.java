package report.friction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AreaNotFoundException extends RuntimeException{

    public AreaNotFoundException(String message){
        super(message);
    }

}
