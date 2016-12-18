package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.entity.Employee;

import java.util.List;

public interface EmployeeDAO extends UserDAO {

    void create(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);

    Employee findById(Long id);

    Employee findByEmail(String email);

    List<Employee> findByName(String firstName, String secondName);

    List<Employee> findAll();
}
