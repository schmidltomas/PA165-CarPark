package cz.muni.fi.pa165.carpark.service.facade;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cz.muni.fi.pa165.carpark.api.dto.CarDTO;
import cz.muni.fi.pa165.carpark.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carpark.api.dto.ReservationDTO;
import cz.muni.fi.pa165.carpark.persistence.dao.ReservationDAO;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.carpark.service.service.ReservationService;
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

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * Created by robot on 25.11.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ReservationFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private ReservationService reservationService;

    @Mock
    private ReservationDAO reservationDAO;

    @Mock
    private ClassMapper classMapper;

    @InjectMocks
    private ReservationFacadeImpl reservationFacade;

    private ReservationDTO reservationDTO;
    private EmployeeDTO employeeDTO;
    private CarDTO carDTO;
    private ReservationDTO reservationDTO2;
    private CarDTO carDTO2;

    private Car car;
    private Car car2;
    private Employee employee;
    private Reservation reservation;
    private Reservation reservation2;

    @Before
    public void setup(){

        Date startDate = Date.valueOf("2016-10-31");
        Date endDate = Date.valueOf("2016-11-10");
        Date startDate2 = Date.valueOf("2017-01-01");
        Date endDate2 = Date.valueOf("2017-01-15");

        employeeDTO = new EmployeeDTO();
        employeeDTO.setId(Integer.toUnsignedLong(1));
        employeeDTO.setEmail("jn@mail.com");
        employeeDTO.setFirstName("Jan");
        employeeDTO.setSecondName("Novak");
        employeeDTO.setUsername("Jan.Novak");
        employeeDTO.setPassword("0000");

        employee = new Employee();
        employee.setId(Integer.toUnsignedLong(1));
        employee.setEmail("jn@mail.com");
        employee.setFirstName("Jan");
        employee.setSecondName("Novak");
        employee.setUsername("Jan.Novak");
        employee.setPassword("0000");

        carDTO = new CarDTO();
        carDTO.setId(Integer.toUnsignedLong(1));
        carDTO.setBrand("skoda");
        carDTO.setCurrentLocation("Brno");
        carDTO.setEvidenceNumber("B-803");
        carDTO.setFuelConsumption(5.3);
        carDTO.setFuelType("diesel");
        carDTO.setSeats(5);
        carDTO.setHomeLocation("Brno");

        carDTO2 = new CarDTO();
        carDTO2.setId(Integer.toUnsignedLong(2));
        carDTO2.setBrand("bmw");
        carDTO2.setCurrentLocation("Praha");
        carDTO2.setEvidenceNumber("B-222");
        carDTO2.setFuelConsumption(7.2);
        carDTO2.setFuelType("diesel");
        carDTO2.setSeats(5);
        carDTO2.setHomeLocation("Brno");

        car = new Car();
        car.setId(Integer.toUnsignedLong(1));
        car.setBrand("skoda");
        car.setCurrentLocation("Brno");
        car.setEvidenceNumber("B-803");
        car.setFuelConsumption(5.3);
        car.setFuelType("diesel");
        car.setSeats(5);
        car.setHomeLocation("Brno");

        car2 = new Car();
        car2.setId(Integer.toUnsignedLong(2));
        car2.setBrand("bmw");
        car2.setCurrentLocation("Praha");
        car2.setEvidenceNumber("B-222");
        car2.setFuelConsumption(7.2);
        car2.setFuelType("diesel");
        car2.setSeats(5);
        car2.setHomeLocation("Brno");

        reservationDTO = new ReservationDTO();
        reservationDTO.setId(Integer.toUnsignedLong(1));
        reservationDTO.setCar(carDTO);
        reservationDTO.setDistance(250);
        reservationDTO.setEmployee(employeeDTO);
        reservationDTO.setPurpose("work meeting");
        reservationDTO.setStartDate(startDate);
        reservationDTO.setEndDate(endDate);

        reservationDTO2 = new ReservationDTO();
        reservationDTO2.setId(Integer.toUnsignedLong(2));
        reservationDTO2.setCar(carDTO2);
        reservationDTO2.setDistance(125);
        reservationDTO2.setEmployee(employeeDTO);
        reservationDTO2.setPurpose("conference");
        reservationDTO2.setStartDate(startDate2);
        reservationDTO2.setEndDate(endDate2);

        reservation = new Reservation();
        reservation.setId(Integer.toUnsignedLong(1));
        reservation.setCar(car);
        reservation.setDistance(250);
        reservation.setEmployee(employee);
        reservation.setPurpose("work meeting");
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);

        reservation2 = new Reservation();
        reservation2.setId(Integer.toUnsignedLong(2));
        reservation2.setCar(car2);
        reservation2.setDistance(125);
        reservation2.setEmployee(employee);
        reservation2.setPurpose("conference");
        reservation2.setStartDate(startDate2);
        reservation2.setEndDate(endDate2);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdTest() {
        when(reservationService.findById(anyLong())).thenReturn(reservation);
        when(classMapper.mapTo(any(Reservation.class), eq(ReservationDTO.class))).thenReturn(reservationDTO);
        final ReservationDTO existingRes = reservationFacade.findById(reservation.getId());
        Assert.assertNotNull(existingRes);
        Assert.assertEquals(reservation.getPurpose(), existingRes.getPurpose());
    }

    @Test
    public void createReservationTest() {
        when(classMapper.mapTo(any(ReservationDTO.class), eq(Reservation.class))).thenReturn(reservation);
        reservationFacade.create(reservationDTO);
        verify(reservationService).create(reservation);
    }

    @Test
    public void updateResTest() {
        when(classMapper.mapTo(any(ReservationDTO.class), eq(Reservation.class))).thenReturn(reservation);
        reservationFacade.update(reservationDTO);
        verify(reservationService).update(reservation);
    }

    @Test
    public void removeResTest() {
        when(classMapper.mapTo(any(ReservationDTO.class), eq(Reservation.class))).thenReturn(reservation);
        reservationFacade.delete(reservationDTO);
        verify(reservationService).delete(reservation);
    }

   @Test
    public void findAllTest() {
        when(reservationService.getAll()).thenReturn(Arrays.asList(reservation, reservation2));
        when(classMapper.mapTo(anyCollectionOf(Car.class),
                eq(ReservationDTO.class))).thenReturn(Arrays.asList(reservationDTO, reservationDTO2));
        final List<ReservationDTO> reservationDTOs = reservationFacade.getAll();
        Assert.assertNotNull(reservationDTOs);
        Assert.assertEquals(2, reservationDTOs.size());
    }
}
