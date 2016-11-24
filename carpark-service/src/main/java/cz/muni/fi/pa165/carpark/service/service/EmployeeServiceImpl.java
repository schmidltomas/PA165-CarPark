/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.service.service;

import com.sun.tracing.dtrace.Attributes;
import cz.muni.fi.pa165.carpark.persistence.dao.CarDao;
import cz.muni.fi.pa165.carpark.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carpark.persistence.dao.ReservationDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import java.util.Collection;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jakub Kriz
 */
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;
    
    @Autowired
    private CarDao carDao;
    
    @Autowired ReservationDao reservationDao;
    
    @Override
    public void createEmployee(Employee employee) {
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
    public long makeReservation(Set<Employee> participants, Date departureTime, String departureLocation, String endLocation, Date freeFrom, Car PreferedCar) {
        List<Car> cars = carDao.findByHomeLocation(departureLocation);
        if(cars==null){
            throw new RuntimeException("there are no available cars at the time and location you chose");
        }
        List<Reservation> reservationsAtTheSameTime = reservationDao.getReservations(departureTime, freeFrom);
        for(Reservation reservation : reservationsAtTheSameTime){
            if(cars.contains(reservation.getCar())){
                cars.remove(reservation.getCar());
            }
        }
        if(cars==null || cars.size() < 1){
            throw new RuntimeException("there are no available cars at the time and location you chose");
        }
        Reservation reservation = new Reservation();
        if(PreferedCar != null){
            if(!cars.contains(PreferedCar)){
                throw new RuntimeException("car that you chose isnt available choose different one or let the system choose");
            }
            reservation.setCar(PreferedCar);
        }
        else{
            for(Car car : cars){
                if(car.getSeats()>participants.size()){
                    reservation.setCar(car);
                    break;
                }
            }
            if(reservation.getCar() == null){
                throw new RuntimeException("car with seat capacity for all participants wasnt found consider making more reservations");
            }
        }
        Employee employee = (Employee) participants.toArray()[0];
        reservation.setEmployee(employee);
        reservation.setEndDate(freeFrom);
        reservation.setStartDate(departureTime);
        reservation.setPurpose(endLocation);
        reservation.setDistance(0);
        long reservationId = reservationDao.create(reservation);
        employee.addReservation(reservation);
        employeeDao.update(employee);
        return reservationId;
    }
    
}
