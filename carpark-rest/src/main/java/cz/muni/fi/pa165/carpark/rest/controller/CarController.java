package cz.muni.fi.pa165.carpark.rest.controller;

import cz.muni.fi.pa165.carpark.api.dto.CarDTO;
import cz.muni.fi.pa165.carpark.api.facade.CarFacade;
import cz.muni.fi.pa165.carpark.rest.utils.ControllerResponse;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Created by karelfajkus on 03/12/2016.
 */
@Controller
@RequestMapping(value = "/pa165/rest/car/")
public class CarController {

    @Autowired
    private CarFacade carFacade;

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public ResponseEntity getAllCars() {
        return ResponseEntity.ok(carFacade.findAll());
    }

    @RequestMapping(value = "getById", method = RequestMethod.GET, params = "id")
    public ResponseEntity getCarById(@Min(0) @RequestParam("id") Long id) throws Exception {
        final CarDTO car = carFacade.findById(id);
        return ControllerResponse.processResponse(car);
    }

    @RequestMapping(value = "getBySpz", method = RequestMethod.GET, params = "spz")
    public ResponseEntity getCarBySpz(@NotBlank @RequestParam("spz") String spz) throws Exception {
        final CarDTO car = carFacade.findBySpz(spz);
        return ControllerResponse.processResponse(car);
    }

    @RequestMapping(value = "getByLocation", method = RequestMethod.GET, params = "location")
    public ResponseEntity getCarByLocation(@NotBlank @RequestParam("location") String location) throws Exception {
        final List<CarDTO> cars = carFacade.findByHomeLocation(location);
        return ControllerResponse.processResponse(cars);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity createCar(@Valid @RequestBody CarDTO request) throws Exception {
        return ControllerResponse.processResponse(carFacade.registerNewCar(request));
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity updateCar(@Valid @RequestBody CarDTO request) throws Exception {
        
        if (request.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Field ID is missing.");
        }
        final CarDTO carToUpdate = carFacade.findById(request.getId());
        if (carToUpdate != null) {
            return ControllerResponse.processResponse(carFacade.updateCar(request));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeCar(@Min(0) @PathVariable Long id) {
        if (carFacade.removeCar(id) != null) {
            return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

}
