package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarDao {

    /**
     * @param car Car
     */
    public void create(Car car);

    /**
     * @param car Car
     */
    public void update(Car car);

    /**
     * @param car Car
     */
    public void delete(Car car);

    /**
     * @param spz String
     * @return Car
     */
    public Car findById(String spz);

    /**
     * @return List<Car>
     */
    public List<Car> findAll();

}
