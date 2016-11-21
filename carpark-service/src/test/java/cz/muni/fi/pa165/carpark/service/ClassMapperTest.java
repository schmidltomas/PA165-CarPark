package cz.muni.fi.pa165.carpark.service;

import cz.muni.fi.pa165.carpark.api.dto.CarDTO;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.carpark.service.utils.mapper.ClassMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by karelfajkus on 20/11/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ClassMapperTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ClassMapper classMapper;

    private Car car;

    private CarDTO carDTO;

    @Before
    public void setUp() {

        carDTO = new CarDTO();
        carDTO.setId(Integer.toUnsignedLong(1));
        carDTO.setBrand("bmw");
        carDTO.setCurrentLocation("Brno");
        carDTO.setEvidenceNumber("HGJ679");
        carDTO.setFuelConsumption(5.6);
        carDTO.setHomeLocation("Praha");
        carDTO.setSeats(5);

        car = new Car();
        car.setId(Integer.toUnsignedLong(1));
        car.setBrand("bmw");
        car.setCurrentLocation("Brno");
        car.setEvidenceNumber("HGJ679");
        car.setFuelConsumption(5.6);
        car.setHomeLocation("Praha");
        car.setSeats(5);
    }

    @Test
    public void carToCarDTOTest() {

        final Car result = classMapper.mapTo(carDTO, Car.class);
        Assert.assertNotNull(result);
        Assert.assertEquals(carDTO.getEvidenceNumber(), result.getEvidenceNumber());
        Assert.assertEquals(carDTO.getId(), result.getId());
    }

    @Test
    public void carDTOToCarTest() {

        final CarDTO result = classMapper.mapTo(car, CarDTO.class);
        Assert.assertNotNull(result);
        Assert.assertEquals(car.getEvidenceNumber(), result.getEvidenceNumber());
        Assert.assertEquals(car.getId(), result.getId());
    }
}
