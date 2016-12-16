package cz.muni.fi.pa165.carpark.rest.controller;

import cz.muni.fi.pa165.carpark.api.dto.ReservationDTO;
import cz.muni.fi.pa165.carpark.api.facade.ReservationFacade;
import cz.muni.fi.pa165.carpark.rest.utils.ControllerResponse;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Created by robot on 15.12.16.
 */
@Controller
@RequestMapping(value = "/pa165/rest/reservation/")
public class ReservationController {
    @Autowired
    private ReservationFacade reservationFacade;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity createReservation(@Valid @RequestBody ReservationDTO request) throws Exception {
        return ControllerResponse.processResponse(reservationFacade.create(request));
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public ResponseEntity getAllReservations() {
        return ResponseEntity.ok(reservationFacade.getAll());
    }

    @RequestMapping(value = "getById", method = RequestMethod.GET, params = "id")
    public ResponseEntity findReservationById(@Min(0) @RequestParam("id") Long id) throws Exception {
        final ReservationDTO reservationDTO = reservationFacade.findById(id);
        return ControllerResponse.processResponse(reservationDTO);
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeReservation(@Min(0) @PathVariable Long id) {
        final ReservationDTO resToRemove = reservationFacade.findById(id);
        if (resToRemove != null) {
            reservationFacade.delete(resToRemove);
            return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity updateReservation(@Valid @RequestBody ReservationDTO request) throws Exception {
        if (request.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Field ID is missing.");
        }
        final ReservationDTO resToUpdate = reservationFacade.findById(request.getId());
        if (resToUpdate != null) {
            return ControllerResponse.processResponse(reservationFacade.update(resToUpdate));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }
}
