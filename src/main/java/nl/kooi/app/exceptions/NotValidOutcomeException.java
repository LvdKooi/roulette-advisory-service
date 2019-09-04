package nl.kooi.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotValidOutcomeException extends RuntimeException{
    public NotValidOutcomeException(String message){
        super(message);
    }


}
