package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.dao.CarDAO;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by karelfajkus on 06/11/2016.
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDAO carDAO;

    @Override
    public void createNewCar(Car car) {
        carDAO.create(car);
    }

    @Override
    public void deleteCar(Car car) {
        carDAO.delete(car);
    }

    @Override
    public void updateCar(Car car) {
        carDAO.update(car);
    }

    @Override
    public Car findById(Long id) {
        return carDAO.findById(id);
    }

    @Override
    public Car findBySpz(String spz) {
        return carDAO.findBySpz(spz);
    }

    @Override
    public List<Car> findAll() {
        return carDAO.findAll();
    }

    @Override
    public List<Car> findByHomeLocation(String location) {
        return carDAO.findByHomeLocation(location);
    }
}
