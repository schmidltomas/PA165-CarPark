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
public class CarDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CarDAO carDAO;

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
        car.setModel("a3");
        car.setCurrentLocation("Praha");
        car.setEvidenceNumber(evidenceNumber);
        car.setFuelConsumption(4.8);
        car.setFuelType("diesel");
        car.setSeats(5);
        car.setHomeLocation("Brno");

        secondCar = new Car();
        secondCar.setBrand("bmw");
        secondCar.setModel("x5");
        secondCar.setCurrentLocation("Praha");
        secondCar.setEvidenceNumber(secondEvidenceNumber);
        secondCar.setFuelConsumption(4.5);
        secondCar.setFuelType("diesel");
        secondCar.setSeats(5);
        secondCar.setHomeLocation("Brno");

        carDAO.create(secondCar);
        carDAO.create(car);
    }
    
    @Test
    public void homeLocationTest(){
        Assert.assertEquals(2, carDAO.findByHomeLocation("Brno").size());
    }

    @Test
    public void testGetCar() {
        Assert.assertNotNull(carDAO.findById(car.getId()));
    }

    @Test
    public void testRemoveCar() {
        carDAO.delete(car);
        Assert.assertNull(carDAO.findById(car.getId()));
    }

    @Test
    public void testFindAll() {
        Assert.assertEquals(2, carDAO.findAll().size());
    }

    @Test
    public void testUpdateCar() {
        Car updatedCar = carDAO.findBySpz(secondEvidenceNumber);
        updatedCar.setBrand("fiat");
        updatedCar.setCurrentLocation("Ostrava");
        updatedCar.setFuelConsumption(4.5);
        updatedCar.setFuelType("benzin");
        updatedCar.setSeats(3);
        updatedCar.setHomeLocation("Brno");

        carDAO.update(updatedCar);
        Car result = carDAO.findById(secondCar.getId());
        Assert.assertEquals("Ostrava", result.getCurrentLocation());
    }
}
