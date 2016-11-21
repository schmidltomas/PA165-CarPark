/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.service.service;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import java.util.Collection;

/**
 *
 * @author Jakub Kriz
 */
public interface EmployeeService {
    
    void createEmployee(Employee employee);
    
    void updateEmployee(Employee employee);
    
    void deleteEmployee(Employee employee);
    
    Employee findById(Long id);
    
    Collection<Employee> findByName(String firstName, String secondName);
    
    Collection<Employee> findAllEmployees();
    
}
