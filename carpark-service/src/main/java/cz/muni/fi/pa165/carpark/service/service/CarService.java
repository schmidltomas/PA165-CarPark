package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by karelfajkus on 06/11/2016.
 */
@Service
public interface CarService {

    public void createNewCar(Car car);

    public void deleteCar(Car car);

    public void updateCar(Car car);

    public Car findById(Long id);

    public Car findBySpz(String spz);

    public List<Car> findAll();
}
