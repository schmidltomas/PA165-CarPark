package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarDao {

    /**
     * Saves entity into db
     *
     * @param car Car
     */
    public void create(Car car);

    /**
     * Saves updated entity into db
     *
     * @param car Car
     */
    public void update(Car car);

    /**
     * Removes entity from db
     *
     * @param car Car
     */
    public void delete(Car car);

    /**
     * Finds entity by id
     *
     * @param id Long
     * @return Car
     */
    public Car findById(Long id);


    /**
     * Finds entity by spz
     *
     * @param spz String
     * @return Car
     */
    public Car findBySpz(String spz);

    /**
     * Returns all entities
     *
     * @return List<Car>
     */
    public List<Car> findAll();

}
