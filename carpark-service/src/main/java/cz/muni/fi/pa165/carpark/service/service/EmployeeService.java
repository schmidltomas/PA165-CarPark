/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.service.service;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.service.utils.AuthnResponse;

import java.util.*;

/**
 *
 * @author Jakub Kriz
 */
public interface EmployeeService {
    
    void createEmployee(Employee employee);
    
    void updateEmployee(Employee employee);
    
    void deleteEmployee(Employee employee);
    
    Employee findById(Long id);

    Employee findByEmail(String email);

    Collection<Employee> findByName(String firstName, String secondName);

    Collection<Employee> findAllEmployees();

    long makeReservation(Employee employee, int seats, Date departureTime, String departureLocation, long distance, String purpose, Date freeFrom, Car preferedCar);

    List<Car> getMostUsedCars(int number);

    AuthnResponse authenticate(String email, String password);
}
