package nl.kooi.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotValidOutcomeException extends RuntimeException{
    public NotValidOutcomeException(String message){
        super(message);
    }


}
