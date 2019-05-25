package nl.kooi.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class nonExistingSessionsException extends RuntimeException {
    public nonExistingSessionsException(String message){
        super(message);
    }

}
