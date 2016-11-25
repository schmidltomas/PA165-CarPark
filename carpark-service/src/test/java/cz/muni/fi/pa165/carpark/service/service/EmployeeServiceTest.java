package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.dao.CarDao;
import cz.muni.fi.pa165.carpark.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carpark.persistence.dao.ReservationDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import cz.muni.fi.pa165.carpark.persistence.repository.CarRepository;
import cz.muni.fi.pa165.carpark.persistence.repository.ReservationRepository;
import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.carpark.service.service.exception.CarParkServiceException;

import java.util.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 *
 * @author Jakub Kriz
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EmployeeServiceTest extends AbstractTestNGSpringContextTests{

    @Mock
    private EmployeeDao employeeDao;
    
    private CarDao carDao;
    
    private ReservationDao reservationDao;
    
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    
    private Employee employee;
    
    private Car car;

    private Car secondCar;

    private Reservation reservation;

    private Reservation secondReservation;

    private Reservation thirdReservation;

    private Date startDate;
    
    private Date endDate;

    private List<Car> carList;
    
    @Before
    public void createEmployee() {
        reservationDao = Mockito.mock(ReservationRepository.class);
        carDao = Mockito.mock(CarRepository.class);
        
        employee = new Employee();
        employee.setEmail("JFK@email.com");
        employee.setFirstName("John");
        employee.setSecondName("Kennedy");
        employee.setUsername("JFK");
        employee.setPassword("strongPass");
        employee.setId(new Long(1));
        
        car = new Car();
        car.setId(Integer.toUnsignedLong(1));
        car.setBrand("bmw");
        car.setCurrentLocation("Praha");
        car.setEvidenceNumber("HGJ679");
        car.setFuelConsumption(5.6);
        car.setHomeLocation("Praha");
        car.setSeats(5);

        secondCar = new Car();
        secondCar.setId(Integer.toUnsignedLong(2));
        secondCar.setBrand("audi");
        secondCar.setCurrentLocation("Brno");
        secondCar.setEvidenceNumber("HG789679");
        secondCar.setFuelConsumption(5.6);
        secondCar.setHomeLocation("Praha");
        secondCar.setSeats(5);

        reservation = new Reservation();
        reservation.setId(Integer.toUnsignedLong(1));
        reservation.setCar(car);

        secondReservation = new Reservation();
        secondReservation.setId(Integer.toUnsignedLong(2));
        secondReservation.setCar(car);

        thirdReservation = new Reservation();
        thirdReservation.setId(Integer.toUnsignedLong(3));
        thirdReservation.setCar(secondCar);
        
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 10, 10);
        startDate = cal.getTime();
        cal.set(2016, 10, 12);
        endDate = cal.getTime();
        
        carList = new ArrayList<>();
        carList.add(car);
        
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void createNewEmployee(){
        employeeService.createEmployee(employee);
        verify(employeeDao).create(any(Employee.class));
    }
    
    @Test
    public void makeReservationTest(){
        doNothing().when(employeeDao).update(any(Employee.class));
        when(reservationDao.create(any(Reservation.class))).thenReturn(1L);
        when(carDao.findByHomeLocation("Praha")).thenReturn(carList);
        long l = employeeService.makeReservation(employee, 3, startDate, "Praha", 25, "really good purpose", endDate, car);
        Assert.assertNotNull(l);
    }
    
    @Test(expected = CarParkServiceException.class)
    public void makeReservationTooManySeatsTest(){
        doNothing().when(employeeDao).update(any(Employee.class));
        when(reservationDao.create(any(Reservation.class))).thenReturn(1L);
        when(carDao.findByHomeLocation("Praha")).thenReturn(carList);
        long l = employeeService.makeReservation(employee, 7, startDate, "Praha", 25, "really good purpose", endDate, null);
    }
    
    @Test(expected = CarParkServiceException.class)
    public void makeReservationNoCarsAtLocationTest(){
        doNothing().when(employeeDao).update(any(Employee.class));
        when(reservationDao.create(any(Reservation.class))).thenReturn(1L);
        when(carDao.findByHomeLocation("Praha")).thenReturn(carList);
        long l = employeeService.makeReservation(employee, 7, startDate, "Brno", 25, "really good purpose", endDate, null);
    }

    @Test
    public void getMostUsedCarsTest() {
        when(reservationDao.getAll()).thenReturn(Arrays.asList(reservation, secondReservation, thirdReservation));
        when(carDao.findBySpz(car.getEvidenceNumber())).thenReturn(car);
        when(carDao.findBySpz(secondCar.getEvidenceNumber())).thenReturn(secondCar);

        final List<Car> mostUsedCars = employeeService.getMostUsedCars(1);
        Assert.assertNotNull(mostUsedCars);
        Assert.assertEquals(car.getEvidenceNumber(), mostUsedCars.get(0).getEvidenceNumber());
    }

    @Test(expected = CarParkServiceException.class)
    public void getMostUsedCarsNoFound() {
        when(reservationDao.getAll()).thenReturn(Collections.emptyList());
        employeeService.getMostUsedCars(1);
        Mockito.verify(carDao, never()).findBySpz(anyString());
    }
}
