/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.api.facade;

import cz.muni.fi.pa165.carpark.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Jakub Kriz
 */
public interface EmployeeFacade extends UserFacade {
    
    void create(EmployeeDTO eployeeDTO);
    
    /**
     * 
     * @param participants people going (mostly for count that will make chosing car easyer)
     * @param departureTime
     * @param departureLocation
     * @param endLocation where will be the car available again
     * @param freeFrom when will be the car available again
     * @param PreferedCar can be null if non prefered
     * @return id of reservation
     */
    long makeReservation(Set<Employee> participants, Date departureTime, String departureLocation, String endLocation, Date freeFrom, Car PreferedCar);

    void update(EmployeeDTO employeeDTO);
    
    void delete(EmployeeDTO employeeDTO);
    
    EmployeeDTO findById(Long userId);
    
    Collection<EmployeeDTO> findByName(String firstName, String secondName);
    
    Collection<EmployeeDTO> findAllEmployees();
}
