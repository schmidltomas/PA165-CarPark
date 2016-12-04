package cz.muni.fi.pa165.carpark.service.facade;

import cz.muni.fi.pa165.carpark.api.dto.CarDTO;
import cz.muni.fi.pa165.carpark.api.facade.CarFacade;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.service.service.CarService;
import cz.muni.fi.pa165.carpark.service.utils.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by karelfajkus on 18/11/2016.
 */
@Service
@Transactional
public class CarFacadeImpl implements CarFacade {

    @Autowired
    private CarService carService;

    @Autowired
    private ClassMapper classMapper;

    @Override
    public CarDTO findById(Long id) {
        final Car car = carService.findById(id);
        return car == null ? null : classMapper.mapTo(car, CarDTO.class);
    }

    @Override
    public CarDTO findBySpz(String spz) {
        final Car car = carService.findBySpz(spz);
        return car == null ? null : classMapper.mapTo(car, CarDTO.class);
    }

    @Override
    public List<CarDTO> findAll() {
        final List<Car> cars = carService.findAll();
        return classMapper.mapTo(cars, CarDTO.class);
    }

    @Override
    public CarDTO registerNewCar(CarDTO car) {
        final Car newCar = classMapper.mapTo(car, Car.class);
        carService.createNewCar(newCar);
        return classMapper.mapTo(newCar, CarDTO.class);
    }

    @Override
    public void removeCar(CarDTO car) {
        final Car carToRemove = classMapper.mapTo(car, Car.class);
        carService.deleteCar(carToRemove);
    }

    @Override
    public CarDTO updateCar(CarDTO car) {
        final Car updatedCar = classMapper.mapTo(car, Car.class);
        carService.updateCar(updatedCar);
        return classMapper.mapTo(updatedCar, CarDTO.class);
    }

    @Override
    public List<CarDTO> findByHomeLocation(String location) {
        final List<Car> cars = carService.findByHomeLocation(location);
        return classMapper.mapTo(cars, CarDTO.class);
    }
}
