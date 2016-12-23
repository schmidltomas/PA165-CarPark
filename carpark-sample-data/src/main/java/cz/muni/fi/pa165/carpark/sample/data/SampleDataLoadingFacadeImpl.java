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
import cz.muni.fi.pa165.carpark.persistence.entity.UserRole;
import cz.muni.fi.pa165.carpark.service.service.AdminService;
import cz.muni.fi.pa165.carpark.service.service.CarService;
import cz.muni.fi.pa165.carpark.service.service.EmployeeService;
import cz.muni.fi.pa165.carpark.service.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        createEmployee("jenda", "12345", "Jan", "Novák", "novak@company.com");
        createEmployee("petr.dvorak", "hunter2", "Petr", "Dvořák", "dvorak@company.com");
        createEmployee("jana", "abcde54321", "Jana", "Svobodová", "svobodova@company.com");
    }

    private void createCars() {
        createCar("2B38206", "BMW", "diesel", 6.7, 5, "Brno", "Brno");
        createCar("7A21534", "Audi", "petrol", 10, 2, "Praha", "Brno");
        createCar("8B30395", "Škoda", "diesel", 4.2, 8, "Brno", "Praha");
        createCar("1A69031", "Kia", "petrol", 5, 5, "Praha", "Praha");
        createCar("4B27372", "Mercedes", "petrol", 8.5, 4, "Brno", "Brno");
        createCar("3B13073", "Ferrari", "petrol", 15, 2, "Brno", "Brno");
    }

    private void createAdmin() {
        createAdmin("admin", "pG4cvK5pSY38", "Pavel", "Černý", "admin@company.com");
        createAdmin("root", "aAVrEszft3Se", "Martin", "Veselý", "vesely@company.com");
    }

    private void createAdmin(String username, String password, String name, String surname, String email) {
        final Admin admin = new Admin();
        admin.setEmail(email);
        admin.setFirstName(name);
        admin.setPassword(password);
        admin.setSecondName(surname);
        admin.setUsername(username);
        admin.setUserRole(UserRole.ROLE_ADMIN);
        adminService.create(admin);
    }

    private void createReservations() throws ParseException {
        final DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        createReservation(allCars.get(0), allEmployees.get(0), format.parse("01.12.2016"), format.parse("01.12.2016"),
                120, "Marketing presentation.");
        createReservation(allCars.get(1), allEmployees.get(1), format.parse("15.12.2016"), format.parse("16.12.2016"),
                150, "Christmas party.");
        createReservation(allCars.get(2), allEmployees.get(2), format.parse("09.01.2017"), format.parse("16.01.2017"),
                85, "Business trip.");
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
