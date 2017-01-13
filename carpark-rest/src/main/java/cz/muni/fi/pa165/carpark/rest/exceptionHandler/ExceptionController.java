package cz.muni.fi.pa165.carpark.rest.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.InvalidParameterException;

/**
 * Created by karelfajkus on 13/01/2017.
 */
@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidParameterException.class)
    public void handleException() {
    }
}
