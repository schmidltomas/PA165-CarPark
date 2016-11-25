package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.configuration.PersistenceConfiguration;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
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
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author Robert Taptik
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersistenceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ReservationDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private CarDao carDao;

    private Reservation reservation;
    private Reservation reservation2;
    private Employee employee;
    private Car car;
    private Car car2;

    @Before
    public void createReservations() {
        reservation = new Reservation();
        reservation2 = new Reservation();
        employee = new Employee();
        car = new Car();
        car2 = new Car();

        Calendar cal = Calendar.getInstance();
        cal.set(2016, 10, 31);
        Date startDate = cal.getTime();
        cal.set(2016, 11, 10);
        Date endDate = cal.getTime();
        cal.set(2017, 1, 1);
        Date startDate2 = cal.getTime();
        cal.set(2017,1,15);
        Date endDate2 = cal.getTime();

        car.setBrand("skoda");
        car.setCurrentLocation("Brno");
        car.setEvidenceNumber("B-803");
        car.setFuelConsumption(5.3);
        car.setFuelType("diesel");
        car.setSeats(5);
        car.setHomeLocation("Brno");

        car2.setBrand("bmw");
        car2.setCurrentLocation("Praha");
        car2.setEvidenceNumber("B-222");
        car2.setFuelConsumption(7.2);
        car2.setFuelType("diesel");
        car2.setSeats(5);
        car2.setHomeLocation("Brno");

        carDao.create(car);
        carDao.create(car2);

        employee.setEmail("jn@mail.com");
        employee.setFirstName("Jan");
        employee.setSecondName("Novak");
        employee.setUsername("Jan.Novak");
        employee.setPassword("0000");
        employeeDao.create(employee);

        reservation.setCar(car);
        reservation.setDistance(250);
        reservation.setEmployee(employee);
        reservation.setPurpose("work meeting");
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);

        reservation2.setCar(car2);
        reservation2.setDistance(125);
        reservation2.setEmployee(employee);
        reservation2.setPurpose("conference");
        reservation2.setStartDate(startDate2);
        reservation2.setEndDate(endDate2);

        reservationDao.create(reservation);
        reservationDao.create(reservation2);
    }

    @Test
    public void reservationAttributes() {
        Reservation res = em.find(Reservation.class, reservation.getId());
        Assert.assertEquals(res.getDistance(), 250);
        Employee reservationEmployee = res.getEmployee();
        Assert.assertNotNull(res.getCar());
        Assert.assertEquals(res.getCar(), car);
        Assert.assertNotNull(reservationEmployee);
        Assert.assertEquals(reservationEmployee.getFirstName(), "Jan");
        Assert.assertEquals(reservationEmployee.getSecondName(), "Novak");
        Assert.assertEquals(reservationEmployee.getEmail(), "jn@mail.com");
        Assert.assertEquals(reservationEmployee.getPassword(), "0000");
        Assert.assertEquals(reservationEmployee.getUsername(), "Jan.Novak");
    }

    @Test
    public void testGetReservation() {
        Assert.assertNotNull(reservationDao.findById(reservation.getId()));
    }

    @Test
    public void testRemoveReservation() {
        reservationDao.delete(reservation);
        Assert.assertNull(reservationDao.findById(reservation.getId()));
    }

    @Test
    public void testUpdateReservation() {
        reservation.setPurpose("holiday");
        reservationDao.update(reservation);
        Reservation foundReservation = em.find(Reservation.class, reservation.getId());
        Assert.assertEquals(foundReservation.getPurpose(), "holiday");
    }

    @Test
    public void testFindAllReservations() {
        Assert.assertEquals(2, reservationDao.getAll().size());
    }

    @Test
    public void getReservationsForEmployee() {
        Assert.assertNotNull(employee.getId());
        Assert.assertTrue(reservationDao.getReservations(employee).size() == 2);
    }
    
    @Test
    public void getReservationsByDates(){
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 1, 2);
        Date startDate = cal.getTime();
        cal.set(2017, 1, 14);
        Date endDate = cal.getTime();
        
//        startCal.set(Calendar.YEAR, 2016);
//        startCal.set(Calendar.MONTH, 10);
//        startCal.set(Calendar.DAY_OF_MONTH, 30);
//        endCal.set(Calendar.YEAR, 2017);
//        endCal.set(Calendar.MONTH, 10);
//        endCal.set(Calendar.DAY_OF_MONTH, 30);
        
        
        Assert.assertEquals(1, reservationDao.getReservations(startDate, endDate).size());
    }
}
