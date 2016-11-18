package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CarDao {

    /**
     * Saves entity into db
     *
     * @param car Car
     */
    void create(Car car);

    /**
     * Saves updated entity into db
     *
     * @param car Car
     */
    void update(Car car);

    /**
     * Removes entity from db
     *
     * @param car Car
     */
    void delete(Car car);

    /**
     * Finds entity by id
     *
     * @param id Long
     * @return Car
     */
    Car findById(Long id);


    /**
     * Finds entity by spz
     *
     * @param spz String
     * @return Car
     */
    Car findBySpz(String spz);

    /**
     * Returns all entities
     *
     * @return List<Car>
     */
    List<Car> findAll();

}
