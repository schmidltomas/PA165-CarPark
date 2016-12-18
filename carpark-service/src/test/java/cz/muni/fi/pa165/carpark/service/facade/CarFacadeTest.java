package cz.muni.fi.pa165.carpark.service.facade;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cz.muni.fi.pa165.carpark.api.dto.CarDTO;
import cz.muni.fi.pa165.carpark.persistence.dao.CarDAO;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.carpark.service.service.CarService;
import cz.muni.fi.pa165.carpark.service.utils.mapper.ClassMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * Created by karelfajkus on 20/11/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CarFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private CarService carService;

    @Mock
    private CarDAO carDAO;

    @Mock
    private ClassMapper classMapper;

    @InjectMocks
    private CarFacadeImpl carFacade;

    private CarDTO carDTO;

    private CarDTO secondCarDTO;

    private Car car;

    private Car secondCar;

    @Before
    public void createCar() {

        carDTO = new CarDTO();
        carDTO.setId(Integer.toUnsignedLong(1));
        carDTO.setBrand("bmw");
        carDTO.setCurrentLocation("Brno");
        carDTO.setEvidenceNumber("HGJ679");
        carDTO.setFuelConsumption(5.6);
        carDTO.setHomeLocation("Praha");
        carDTO.setSeats(5);

        secondCarDTO = new CarDTO();
        secondCarDTO.setId(Integer.toUnsignedLong(2));
        secondCarDTO.setBrand("audo");
        secondCarDTO.setCurrentLocation("Brno");
        secondCarDTO.setEvidenceNumber("HG789679");
        secondCarDTO.setFuelConsumption(5.6);
        secondCarDTO.setHomeLocation("Praha");
        secondCarDTO.setSeats(5);

        car = new Car();
        car.setId(Integer.toUnsignedLong(1));
        car.setBrand("bmw");
        car.setCurrentLocation("Brno");
        car.setEvidenceNumber("HGJ679");
        car.setFuelConsumption(5.6);
        car.setHomeLocation("Praha");
        car.setSeats(5);

        secondCar = new Car();
        secondCar.setId(Integer.toUnsignedLong(1));
        secondCar.setBrand("audi");
        secondCar.setCurrentLocation("Brno");
        secondCar.setEvidenceNumber("HG789679");
        secondCar.setFuelConsumption(5.6);
        secondCar.setHomeLocation("Praha");
        secondCar.setSeats(5);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdTest() {

        when(carService.findById(anyLong())).thenReturn(car);
        when(classMapper.mapTo(any(Car.class), eq(CarDTO.class))).thenReturn(carDTO);

        final CarDTO existingCar = carFacade.findById(car.getId());
        Assert.assertNotNull(existingCar);
        Assert.assertEquals(car.getBrand(), existingCar.getBrand());
        Assert.assertEquals(car.getFuelConsumption(), existingCar.getFuelConsumption());
        Assert.assertEquals(car.getId(), existingCar.getId());
    }

    @Test
    public void findAllTest() {

        when(carService.findAll()).thenReturn(Arrays.asList(car, secondCar));
        when(classMapper.mapTo(anyCollectionOf(Car.class),
                eq(CarDTO.class))).thenReturn(Arrays.asList(carDTO, secondCarDTO));

        final List<CarDTO> cars = carFacade.findAll();
        Assert.assertNotNull(cars);
        Assert.assertEquals(2, cars.size());
    }

    @Test
    public void findBySpzTest() {

        when(carService.findBySpz(car.getEvidenceNumber())).thenReturn(car);
        when(classMapper.mapTo(any(Car.class), eq(CarDTO.class))).thenReturn(carDTO);

        final CarDTO result = carFacade.findBySpz(car.getEvidenceNumber());
        Assert.assertNotNull(result);
        Assert.assertEquals(car.getEvidenceNumber(), result.getEvidenceNumber());
    }

    @Test
    public void registerNewCarTest() {

        when(classMapper.mapTo(any(CarDTO.class), eq(Car.class))).thenReturn(car);
        carFacade.registerNewCar(carDTO);
        verify(carService).createNewCar(car);
    }

    @Test
    public void RemoveCarTest() {

        when(classMapper.mapTo(any(CarDTO.class), eq(Car.class))).thenReturn(car);
        when(carService.findById(anyLong())).thenReturn(car);
        carFacade.removeCar(car.getId());
        verify(carService).deleteCar(car);
    }

    @Test
    public void UpdateCarTest() {

        when(classMapper.mapTo(any(CarDTO.class), eq(Car.class))).thenReturn(car);
        carFacade.updateCar(carDTO);
        verify(carService).updateCar(car);
    }

}
