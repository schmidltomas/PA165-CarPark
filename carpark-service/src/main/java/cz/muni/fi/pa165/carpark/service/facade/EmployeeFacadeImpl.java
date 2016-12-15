/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.service.facade;

import cz.muni.fi.pa165.carpark.api.dto.CarDTO;
import cz.muni.fi.pa165.carpark.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carpark.api.dto.UserDTO;
import cz.muni.fi.pa165.carpark.api.facade.EmployeeFacade;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.service.service.CarService;
import cz.muni.fi.pa165.carpark.service.service.EmployeeService;
import cz.muni.fi.pa165.carpark.service.utils.mapper.ClassMapper;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jakub Kriz
 */
@Service
@Transactional
public class EmployeeFacadeImpl implements EmployeeFacade {
    
    @Autowired
    private ClassMapper classMapper;
    
    @Autowired 
    private EmployeeService employeeService;
    
    @Autowired
    private CarService carService;

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        final Employee employee =classMapper.mapTo(employeeDTO, Employee.class);
        employeeService.createEmployee(employee);
        return classMapper.mapTo(employee, EmployeeDTO.class);
    }

    @Override
    public long makeReservation(EmployeeDTO employeeDTO, int seats, java.sql.Date departureTime, String departureLocation, long distance, String purpose, Date freeFrom, CarDTO preferedCarDTO) {
        Employee employee = employeeService.findById(employeeDTO.getId());
        Car preferedCar = carService.findById(preferedCarDTO.getId());
        return employeeService.makeReservation(employee, seats, freeFrom, departureLocation, distance, purpose, freeFrom, preferedCar);
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        final Employee employee =classMapper.mapTo(employeeDTO, Employee.class);
        employeeService.updateEmployee(employee);
        return classMapper.mapTo(employee, EmployeeDTO.class);
    }

    @Override
    public void delete(EmployeeDTO employeeDTO) {
        employeeService.deleteEmployee(classMapper.mapTo(employeeDTO, Employee.class));
    }

    @Override
    public EmployeeDTO findById(Long userId) {
        Employee employee = employeeService.findById(userId);
        return (employee == null) ? null : classMapper.mapTo(employee, EmployeeDTO.class);
    }

    @Override
    public Collection<EmployeeDTO> findByName(String firstName, String secondName) {
        Collection<Employee> employee = employeeService.findByName(firstName, secondName);
        return (employee == null) ? null : classMapper.mapTo(employee, EmployeeDTO.class);
    }

    @Override
    public Collection<EmployeeDTO> findAllEmployees() {
        return classMapper.mapTo(employeeService.findAllEmployees(), EmployeeDTO.class);
    }

    
    ///_____________USER STUFF_______________
    @Override
    public void register(UserDTO userDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean authenticate(UserDTO userDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
