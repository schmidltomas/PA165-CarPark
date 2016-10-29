package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import java.util.List;

public interface EmployeeDao extends UserDao {
        
    public void create(Employee employee);
    
    public void update(Employee employee);

    public void delete(Employee employee);

    public Employee findById(Long id);
    
    public List<Employee> findByName(String firstName, String secoundName);

    public List<Employee> findAll();
}
