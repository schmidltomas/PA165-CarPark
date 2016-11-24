package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.dao.CarDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by karelfajkus on 20/11/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CarServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CarDao carDao;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;

    private Car secondCar;

    @Before
    public void setUp() {

        car = new Car();
        car.setId(Integer.toUnsignedLong(1));
        car.setBrand("bmw");
        car.setCurrentLocation("Brno");
        car.setEvidenceNumber("HGJ679");
        car.setFuelConsumption(5.6);
        car.setHomeLocation("Praha");
        car.setSeats(5);

        secondCar = new Car();
        secondCar.setId(Integer.toUnsignedLong(2));
        secondCar.setBrand("bmw");
        secondCar.setCurrentLocation("Brno");
        secondCar.setEvidenceNumber("HGJ679");
        secondCar.setFuelConsumption(5.6);
        secondCar.setHomeLocation("Praha");
        secondCar.setSeats(5);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createNewCarTest() {

        carService.createNewCar(car);
        verify(carDao).create(any(Car.class));
    }

    @Test
    public void updateCarTest() {

        carService.updateCar(car);
        verify(carDao).update(any(Car.class));
    }

    @Test
    public void removeCarTest() {

        carService.deleteCar(car);
        verify(carDao).delete(any(Car.class));
    }

    @Test
    public void findByIdTest() {

        when(carService.findById(anyLong())).thenReturn(car);
        final Car result = carService.findById(car.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(car.getId(), result.getId());
    }

    @Test
    public void findAllTest() {

        when(carService.findAll()).thenReturn(Arrays.asList(car, secondCar));
        final List<Car> cars = carService.findAll();
        Assert.assertNotNull(cars);
        Assert.assertEquals(2, cars.size());
    }

    @Test
    public void findByHomeLocationTest(){
        when(carService.findByHomeLocation("Praha")).thenReturn(Arrays.asList(car, secondCar));
        final List<Car> cars = carService.findByHomeLocation("Praha");
        Assert.assertNotNull(cars);
        Assert.assertEquals(2, cars.size());
    }
    
    @Test
    public void findBySpzTest() {

        when(carService.findBySpz(anyString())).thenReturn(car);
        final Car result = carService.findBySpz(car.getEvidenceNumber());
        Assert.assertNotNull(result);
        Assert.assertEquals(car.getId(), result.getId());
    }

}
