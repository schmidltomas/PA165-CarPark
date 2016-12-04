package cz.muni.fi.pa165.carpark.rest.controller;

import cz.muni.fi.pa165.carpark.api.dto.CarDTO;
import cz.muni.fi.pa165.carpark.api.facade.CarFacade;
import cz.muni.fi.pa165.carpark.rest.utils.ControllerResponse;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

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

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity updateCar(@Valid @RequestBody CarDTO request) throws Exception {
        if (request.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Field ID is missing.");
        }
        final CarDTO carToUpdate = carFacade.findById(request.getId());
        if (carToUpdate != null) {
            return ControllerResponse.processResponse(carFacade.updateCar(carToUpdate));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> updateCar(@Min(0) @PathVariable Long id) {
        final CarDTO carToRemove = carFacade.findById(id);
        if (carToRemove != null) {
            carFacade.removeCar(carToRemove);
            return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

}
