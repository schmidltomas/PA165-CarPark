
package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.dao.CarDAO;
import cz.muni.fi.pa165.carpark.persistence.dao.EmployeeDAO;
import cz.muni.fi.pa165.carpark.persistence.dao.ReservationDAO;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import cz.muni.fi.pa165.carpark.persistence.entity.UserRole;
import cz.muni.fi.pa165.carpark.service.service.exception.CarParkServiceException;
import cz.muni.fi.pa165.carpark.service.utils.AuthnResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Jakub Kriz
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Autowired
    private CarDAO carDAO;
    
    @Autowired
    private ReservationDAO reservationDAO;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public void createEmployee(Employee employee) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setUserRole(UserRole.ROLE_EMPLOYEE);
        employeeDAO.create(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeDAO.delete(employee);
    }

    @Override
    public Employee findById(Long id) {
        return employeeDAO.findById(id);
    }

    @Override
    public Collection<Employee> findByName(String firstName, String secondName) {
        return employeeDAO.findByName(firstName, secondName);
    }

    @Override
    public Collection<Employee> findAllEmployees() {
        return employeeDAO.findAll();
    }

    @Override
    public long makeReservation(Employee employee, int seats, Date departureTime, String departureLocation,
                                long distance, String purpose, Date freeFrom, Car preferedCar) {
        final List<Car> cars = carDAO.findByHomeLocation(departureLocation);
        if (cars == null || cars.isEmpty()) {
            throw new CarParkServiceException("there are no available cars at the time and location you chose");
        }

        final List<Reservation> reservationsAtTheSameTime = reservationDAO.getReservations(departureTime, freeFrom);
        reservationsAtTheSameTime.stream()
                .filter(reservation -> cars.contains(reservation.getCar()))
                .forEach(reservation -> cars.remove(reservation.getCar()));

        final Reservation reservation = new Reservation();
        if (preferedCar != null) {
            if (!cars.contains(preferedCar)) {
                throw new CarParkServiceException("car that you chose is not available choose different one or let the system choose");
            }
            reservation.setCar(preferedCar);
        } else {
            reservation.setCar(cars.stream()
                    .filter(car -> car.getSeats() > seats)
                    .findFirst()
                    .orElse(null));

            if (reservation.getCar() == null) {
                throw new CarParkServiceException("car with seat capacity for all participants was not found consider making more reservations");
            }
        }
        reservation.setEmployee(employee);
        reservation.setEndDate(freeFrom);
        reservation.setStartDate(departureTime);
        reservation.setPurpose(purpose);
        reservation.setDistance(distance);

        final long reservationId = reservationDAO.create(reservation);
        employee.addReservation(reservation);
        employeeDAO.update(employee);

        return reservationId;
    }

    @Override
    public List<Car> getMostUsedCars(int number) {
        final Map<String, Integer> carUsageStatistic = new HashMap<>();
        final List<Car> cars = reservationDAO.getAll().stream()
                .map(Reservation::getCar)
                .collect(Collectors.toList());

        if (cars == null || cars.isEmpty()) {
            throw new CarParkServiceException("there are no cars used yet.");
        }

        cars.forEach(car -> {
            Integer numberOfUsages = carUsageStatistic.get(car.getEvidenceNumber());
            if (numberOfUsages == null) {
                numberOfUsages = 1;
            } else {
                numberOfUsages++;
            }
            carUsageStatistic.put(car.getEvidenceNumber(), numberOfUsages);
        });
        return carUsageStatistic.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .limit(number)
                .map(entry -> carDAO.findBySpz(entry.getKey()))
                .collect(Collectors.toList());
    }

    @Override
    public AuthnResponse authenticate(String email, String password) {
        Employee employee = employeeDAO.findByEmail(email);

        if (employee == null) {
            return AuthnResponse.NOT_FOUND;
        } else {
            String encodedPassword = employee.getPassword();
            if (!bCryptPasswordEncoder.matches(password, encodedPassword)) {
                return AuthnResponse.INCORRECT_PASSWORD;
            }
        }

        return AuthnResponse.OK;
    }
}
