/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.service.facade;

import cz.muni.fi.pa165.carpark.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carpark.api.dto.UserDTO;
import cz.muni.fi.pa165.carpark.api.facade.EmployeeFacade;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.service.service.EmployeeService;
import cz.muni.fi.pa165.carpark.service.utils.mapper.ClassMapper;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jakub Kriz
 */
public class EmployeeFacadeImpl implements EmployeeFacade {
    
    @Autowired
    private ClassMapper classMapper;
    
    @Autowired 
    private EmployeeService employeeService;

    @Override
    public void create(EmployeeDTO eployeeDTO) {
        employeeService.createEmployee(classMapper.mapTo(eployeeDTO, Employee.class));
    }

    @Override
    public long makeReservation(Set<Employee> participants, Date departureTime, String departureLocation, String endLocation, Date freeFrom, Car PreferedCar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(classMapper.mapTo(employeeDTO, Employee.class));
    }

    @Override
    public void delete(long id) {
        employeeService.deleteEmployee(employeeService.findById(id));
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

    @Override
    public void register(UserDTO userDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean authenticate(UserDTO userDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
