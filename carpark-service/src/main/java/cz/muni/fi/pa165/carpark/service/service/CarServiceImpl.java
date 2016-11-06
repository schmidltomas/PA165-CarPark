package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.dao.CarDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by karelfajkus on 06/11/2016.
 */
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public void createNewCar(Car car) {
        carDao.create(car);
    }

    @Override
    public void deleteCar(Car car) {
        carDao.delete(car);
    }

    @Override
    public void updateCar(Car car) {
        carDao.update(car);
    }

    @Override
    public Car findById(Long id) {
        return carDao.findById(id);
    }

    @Override
    public Car findBySpz(String spz) {
        return carDao.findBySpz(spz);
    }

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }
}
