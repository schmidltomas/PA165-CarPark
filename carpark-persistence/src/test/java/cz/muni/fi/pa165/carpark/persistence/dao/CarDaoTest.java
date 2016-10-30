package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.configuration.PersistenceConfiguration;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by karelfajkus on 26/10/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersistenceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CarDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CarDao carDao;

    private Car car;
    private Car secondCar;
    private String evidenceNumber = "4B890";
    private String secondEvidenceNumber = "8BSK6";

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void createCar() {
        car = new Car();
        car.setBrand("audi");
        car.setCurrentLocation("Praha");
        car.setEvidenceNumber(evidenceNumber);
        car.setFuelConsumption(4.8);
        car.setFuelType("diesel");
        car.setSeats(5);
        car.setHomeLocation("Brno");

        secondCar = new Car();
        secondCar.setBrand("bmw");
        secondCar.setCurrentLocation("Praha");
        secondCar.setEvidenceNumber(secondEvidenceNumber);
        secondCar.setFuelConsumption(4.5);
        secondCar.setFuelType("diesel");
        secondCar.setSeats(5);
        secondCar.setHomeLocation("Brno");

        carDao.create(secondCar);
        carDao.create(car);
    }


    @Test
    public void testGetCar() {
        Assert.assertNotNull(carDao.findById(car.getId()));
    }

    @Test
    public void testRemoveCar() {
        carDao.delete(car);
        Assert.assertNull(carDao.findById(car.getId()));
    }

    @Test
    public void testFindAll() {
        Assert.assertEquals(2, carDao.findAll().size());
    }

    @Test
    public void testUpdateCar() {
        Car updatedCar = carDao.findBySpz(secondEvidenceNumber);
        updatedCar.setBrand("fiat");
        updatedCar.setCurrentLocation("Ostrava");
        updatedCar.setFuelConsumption(4.5);
        updatedCar.setFuelType("benzin");
        updatedCar.setSeats(3);
        updatedCar.setHomeLocation("Brno");

        carDao.update(updatedCar);
        Car result = carDao.findById(secondCar.getId());
        Assert.assertEquals("Ostrava", result.getCurrentLocation());
    }
}
