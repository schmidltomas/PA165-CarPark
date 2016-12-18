package cz.muni.fi.pa165.carpark.service.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cz.muni.fi.pa165.carpark.persistence.dao.CarDAO;
import cz.muni.fi.pa165.carpark.persistence.dao.EmployeeDAO;
import cz.muni.fi.pa165.carpark.persistence.dao.ReservationDAO;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * Created by robot on 25.11.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ReservationServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ReservationDAO reservationDAO;

    @Mock
    private EmployeeDAO employeeDAO;

    @Mock
    private CarDAO carDAO;

    @Autowired
    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation;
    private Reservation reservation2;
    private Car car;
    private Car car2;
    private Employee employee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        reservation = new Reservation();
        reservation2 = new Reservation();
        employee = new Employee();
        car = new Car();
        car2 = new Car();

        Date startDate = Date.valueOf("2016-10-31");
        Date endDate = Date.valueOf("2016-11-10");
        Date startDate2 = Date.valueOf("2017-01-01");
        Date endDate2 = Date.valueOf("2017-01-15");

        car.setId(Integer.toUnsignedLong(1));
        car.setBrand("skoda");
        car.setCurrentLocation("Brno");
        car.setEvidenceNumber("B-803");
        car.setFuelConsumption(5.3);
        car.setFuelType("diesel");
        car.setSeats(5);
        car.setHomeLocation("Brno");

        car2.setId(Integer.toUnsignedLong(2));
        car2.setBrand("bmw");
        car2.setCurrentLocation("Praha");
        car2.setEvidenceNumber("B-222");
        car2.setFuelConsumption(7.2);
        car2.setFuelType("diesel");
        car2.setSeats(5);
        car2.setHomeLocation("Brno");

        employee.setId(Integer.toUnsignedLong(1));
        employee.setEmail("jn@mail.com");
        employee.setFirstName("Jan");
        employee.setSecondName("Novak");
        employee.setUsername("Jan.Novak");
        employee.setPassword("0000");
        employeeDAO.create(employee);

        reservation.setId(Integer.toUnsignedLong(1));
        reservation.setCar(car);
        reservation.setDistance(250);
        reservation.setEmployee(employee);
        reservation.setPurpose("work meeting");
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);

        reservation2.setId(Integer.toUnsignedLong(2));
        reservation2.setCar(car2);
        reservation2.setDistance(125);
        reservation2.setEmployee(employee);
        reservation2.setPurpose("conference");
        reservation2.setStartDate(startDate2);
        reservation2.setEndDate(endDate2);
    }

    @Test
    public void createReservationTest() {
        reservationService.create(reservation);
        verify(reservationDAO).create(any(Reservation.class));
    }

    @Test
    public void deleteReservationTest() {
        reservationService.delete(reservation);
        verify(reservationDAO).delete(reservation);
    }

    @Test
    public void updateResTest() {
        reservationService.update(reservation);
        verify(reservationDAO).update(any(Reservation.class));
    }

    @Test
    public void findByIdTest() {
        Long id = reservation.getId();
        when(reservationDAO.findById(any(Long.class))).thenReturn(reservation);
        reservationService.findById(id);
        verify(reservationDAO).findById(id);
    }

    @Test
    public void findAll() {
        when(reservationService.getAll()).thenReturn(Arrays.asList(reservation, reservation2));
        final List<Reservation> res = reservationService.getAll();
        Assert.assertNotNull(res);
        Assert.assertEquals(2, res.size());
        verify(reservationDAO).getAll();
    }
}
