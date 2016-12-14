/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.sample.data;

import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.service.service.EmployeeService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jakub Kriz
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade{

    @Autowired
    EmployeeService employeeService;
    
    public void loadData() throws IOException {
        Employee employee1 = employee("employee", "employee", "jmeno_1", "prijmeni_1", "employee@company.com");
        Employee employee2 = employee("employee2", "employee", "jmeno_2", "prijmeni_2", "employee2@company.com");
        Employee employee3 = employee("employee3", "employee", "jmeno_3", "prijmeni_3", "employee3@company.com");
    }

    private Employee employee(String username, String password, String name, String surname, String email) {
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setFirstName(name);
        employee.setPassword(password);
        employee.setSecondName(surname);
        employee.setUsername(username);
        employeeService.createEmployee(employee);
        return employee;
    }
    
}
