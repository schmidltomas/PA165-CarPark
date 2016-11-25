package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by karelfajkus on 06/11/2016.
 */
public interface CarService {

    void createNewCar(Car car);

    void deleteCar(Car car);

    void updateCar(Car car);

    Car findById(Long id);

    Car findBySpz(String spz);
    
    List<Car> findByHomeLocation(String location);

    List<Car> findAll();
}
