
package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.dao.CarDao;
import cz.muni.fi.pa165.carpark.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carpark.persistence.dao.ReservationDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import cz.muni.fi.pa165.carpark.persistence.entity.UserRole;
import cz.muni.fi.pa165.carpark.service.service.exception.CarParkServiceException;
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
    private EmployeeDao employeeDao;
    
    @Autowired
    private CarDao carDao;
    
    @Autowired
    private ReservationDao reservationDao;
    
    @Override
    public void createEmployee(Employee employee) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setUserRole(UserRole.ROLE_USER);
        employeeDao.create(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeDao.delete(employee);
    }

    @Override
    public Employee findById(Long id) {
        return employeeDao.findById(id);
    }

    @Override
    public Collection<Employee> findByName(String firstName, String secondName) {
        return employeeDao.findByName(firstName, secondName);
    }

    @Override
    public Collection<Employee> findAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public long makeReservation(Employee employee, int seats, Date departureTime, String departureLocation,
                                long distance, String purpose, Date freeFrom, Car preferedCar) {
        final List<Car> cars = carDao.findByHomeLocation(departureLocation);
        if (cars == null || cars.isEmpty()) {
            throw new CarParkServiceException("there are no available cars at the time and location you chose");
        }

        final List<Reservation> reservationsAtTheSameTime = reservationDao.getReservations(departureTime, freeFrom);
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

        final long reservationId = reservationDao.create(reservation);
        employee.addReservation(reservation);
        employeeDao.update(employee);

        return reservationId;
    }

    @Override
    public List<Car> getMostUsedCars(int number) {
        final Map<String, Integer> carUsageStatistic = new HashMap<>();
        final List<Car> cars = reservationDao.getAll().stream()
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
                .map(entry -> carDao.findBySpz(entry.getKey()))
                .collect(Collectors.toList());
    }
}
