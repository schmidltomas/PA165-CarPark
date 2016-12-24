/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.service.facade;

import cz.muni.fi.pa165.carpark.api.dto.CarDTO;
import cz.muni.fi.pa165.carpark.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carpark.api.dto.LoginRequestDTO;
import cz.muni.fi.pa165.carpark.api.dto.LoginResponseDTO;
import cz.muni.fi.pa165.carpark.api.facade.EmployeeFacade;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.UserRole;
import cz.muni.fi.pa165.carpark.service.service.CarService;
import cz.muni.fi.pa165.carpark.service.service.EmployeeService;
import cz.muni.fi.pa165.carpark.service.utils.AuthnResponse;
import cz.muni.fi.pa165.carpark.service.utils.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

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
        final Employee employee = classMapper.mapTo(employeeDTO, Employee.class);
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
    public EmployeeDTO delete(Long id) {
        final Employee employeeToRemove = employeeService.findById(id);
        if (employeeToRemove != null) {
            employeeService.deleteEmployee(employeeToRemove);
            return classMapper.mapTo(employeeToRemove, EmployeeDTO.class);
        }
        return null;
    }

    @Override
    public EmployeeDTO findById(Long userId) {
        Employee employee = employeeService.findById(userId);
        return (employee == null) ? null : classMapper.mapTo(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO findByEmail(String email) {
        Employee employee = employeeService.findByEmail(email);
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
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        AuthnResponse authnResponse = employeeService.authenticate(loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword());

        if (authnResponse.equals(AuthnResponse.OK)) {
            loginResponseDTO.setAuthenticated(true);
            loginResponseDTO.setUserRole(UserRole.ROLE_EMPLOYEE.toString());
        } else {
            if (authnResponse.equals(AuthnResponse.INCORRECT_PASSWORD)) {
                loginResponseDTO.setUserRole(UserRole.ROLE_EMPLOYEE.toString());
                loginResponseDTO.setAuthenticated(false);
            }
        }

        return loginResponseDTO;
    }
}
