/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.sample.data;

import cz.muni.fi.pa165.carpark.persistence.entity.Admin;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import cz.muni.fi.pa165.carpark.service.service.AdminService;
import cz.muni.fi.pa165.carpark.service.service.CarService;
import cz.muni.fi.pa165.carpark.service.service.EmployeeService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cz.muni.fi.pa165.carpark.service.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jakub Kriz
 */
@Service
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade{

    private final static Logger LOGGER = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CarService carService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ReservationService reservationService;

    private List<Car> allCars = new ArrayList<>();

    private List<Employee> allEmployees = new ArrayList<>();
    
    public void loadData() throws Exception {
        createEmployees();
        LOGGER.info("Sample employees created.");

        createCars();
        LOGGER.info("Sample cars created.");

        createAdmin();
        LOGGER.info("Admin created.");

        createReservations();
        LOGGER.info("Sample reservations created.");
    }

    private void createEmployee(String username, String password, String name, String surname, String email) {
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setFirstName(name);
        employee.setPassword(password);
        employee.setSecondName(surname);
        employee.setUsername(username);
        employeeService.createEmployee(employee);
        allEmployees.add(employee);
    }

    private void createCar(String evidenceNumber, String brand, String fuelType, double fuelConsumption,
            int seats, String homeLocation, String currentLocation) {
        final Car car = new Car();
        car.setSeats(seats);
        car.setHomeLocation(homeLocation);
        car.setFuelConsumption(fuelConsumption);
        car.setBrand(brand);
        car.setCurrentLocation(currentLocation);
        car.setFuelType(fuelType);
        car.setEvidenceNumber(evidenceNumber);
        carService.createNewCar(car);
        allCars.add(car);
    }

    private void createEmployees() {
        createEmployee("employee", "employee", "jmeno_1", "prijmeni_1", "employee@company.com");
        createEmployee("employee2", "employee", "jmeno_2", "prijmeni_2", "employee2@company.com");
        createEmployee("employee3", "employee", "jmeno_3", "prijmeni_3", "employee3@company.com");
    }

    private void createCars() {
        createCar("12BG2", "bmw", "diesel", 6.7, 5, "Brno", "Brno");
        createCar("12BG5", "audi", "petrol", 10, 5, "Praha", "Brno");
        createCar("12BG7", "skoda", "diesel", 4.2, 2, "Brno", "Praha");
        createCar("15BG2", "kia", "petrol", 5, 3, "Brno", "Brno");
        createCar("10BG2", "mercedes", "petrol", 8.5, 6, "Brno", "Brno");
        createCar("14BG2", "ferrari", "petrol", 15, 2, "Brno", "Brno");
    }

    private void createAdmin() {
        createAdmin("admin", "admin", "Pepa", "Ultra", "admin@company.com");
    }

    private void createAdmin(String username, String password, String name, String surname, String email) {
        final Admin admin = new Admin();
        admin.setEmail(email);
        admin.setFirstName(name);
        admin.setPassword(password);
        admin.setSecondName(surname);
        admin.setUsername(username);
        adminService.create(admin);
    }

    private void createReservations() throws ParseException {
        final DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        createReservation(allCars.get(0), allEmployees.get(0), format.parse("02.06.2016"), format.parse("04.06.2016"),
                150, "business");
        createReservation(allCars.get(1), allEmployees.get(1), format.parse("06.08.2016"), format.parse("08.08.2016"),
                150, "business");
        createReservation(allCars.get(2), allEmployees.get(2), format.parse("09.06.2016"), format.parse("11.06.2016"),
                150, "business");
    }

    private void createReservation(Car car, Employee employee, Date start, Date end, long distance, String purpose) {
        final Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setDistance(distance);
        reservation.setEmployee(employee);
        reservation.setStartDate(start);
        reservation.setEndDate(end);
        reservation.setPurpose(purpose);
        reservationService.create(reservation);
    }
    
}
