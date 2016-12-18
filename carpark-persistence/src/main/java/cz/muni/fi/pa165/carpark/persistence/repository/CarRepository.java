package cz.muni.fi.pa165.carpark.persistence.repository;

import cz.muni.fi.pa165.carpark.persistence.dao.CarDAO;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by karelfajkus on 26/10/2016.
 */
@Repository
public class CarRepository implements CarDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Car car) {
        entityManager.persist(car);
    }

    @Override
    public void update(Car car) {
        System.out.println(car);
        entityManager.merge(car);
    }

    @Override
    public void delete(Car car) {
        entityManager.remove(car);
    }

    @Override
    public Car findById(Long id) {
        return entityManager.find(Car.class, id);
    }

    @Override
    public Car findBySpz(String spz) {
        return entityManager.createQuery("SELECT car FROM Car car WHERE spz LIKE :spz", Car.class)
                .setParameter("spz", spz)
                .getSingleResult();
    }

    @Override
    public List<Car> findAll() {
        return entityManager.createQuery("SELECT car FROM Car car", Car.class).getResultList();
    }

    @Override
    public List<Car> findByHomeLocation(String location) {
        return entityManager.createQuery("SELECT car FROM Car car WHERE home_location = :home_location", Car.class)
                .setParameter("home_location", location)
                .getResultList();
    }
}
