/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.api.facade;

import cz.muni.fi.pa165.carpark.api.dto.CarDTO;
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
    
    EmployeeDTO create(EmployeeDTO eployeeDTO);
    
    /**
     * 
     * @param employeeDTO
     * @param seats
     * @param departureTime
     * @param departureLocation
     * @param freeFrom when will be the car available again
     * @param purpose
     * @param preferedCarDTO can be null if non prefered
     * @param distance
     * @return id of reservation
     */
    long makeReservation(EmployeeDTO employeeDTO, int seats, java.sql.Date departureTime, String departureLocation, long distance, String purpose, Date freeFrom, CarDTO preferedCarDTO);

    EmployeeDTO update(EmployeeDTO employeeDTO);
    
    EmployeeDTO delete(Long id);
    
    EmployeeDTO findById(Long userId);
    
    Collection<EmployeeDTO> findByName(String firstName, String secondName);
    
    Collection<EmployeeDTO> findAllEmployees();
}
