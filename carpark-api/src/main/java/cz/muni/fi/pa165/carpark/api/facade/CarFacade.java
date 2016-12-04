package cz.muni.fi.pa165.carpark.api.facade;

import cz.muni.fi.pa165.carpark.api.dto.CarDTO;

import java.util.List;

/**
 * Created by karelfajkus on 18/11/2016.
 */
public interface CarFacade {

    CarDTO findById(Long id);

    CarDTO findBySpz(String spz);
    
    List<CarDTO> findByHomeLocation(String location);

    List<CarDTO> findAll();

    CarDTO registerNewCar(CarDTO car);

    void removeCar(CarDTO car);

    CarDTO updateCar(CarDTO car);

}
