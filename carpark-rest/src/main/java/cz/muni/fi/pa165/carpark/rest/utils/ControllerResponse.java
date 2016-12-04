package cz.muni.fi.pa165.carpark.rest.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by karelfajkus on 03/12/2016.
 */
public class ControllerResponse {

    public static <T> ResponseEntity processResponse(T response) throws Exception {
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }
}
