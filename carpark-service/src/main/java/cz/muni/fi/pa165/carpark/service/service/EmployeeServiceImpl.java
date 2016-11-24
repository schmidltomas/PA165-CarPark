/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jakub Kriz
 */
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;
    
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
