
import cz.muni.fi.pa165.carpark.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub Kříž
 */
public class EmployeeDaoTest {
    @Autowired
    private EmployeeDao employeeDao;

    private Employee employee;
    private Employee employee2;
    private String userName = "Pavel.Novak";
    private String userName2 = "Jiri.Novotny";

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void createEmployee() {
        employee = new Employee();
        employee.setEmail("femail@f.com");
        employee.setFirstName("Pavel");
        employee.setLastName("Novak");
        employee.setUsername(userName);
        employee.setPassword("1234");
        employee2 = new Employee();
        employee2.setEmail("femailjiri@f.com");
        employee2.setFirstName("Jiri");
        employee2.setLastName("Novotny");
        employee2.setUsername(userName2);
        employee2.setPassword("321");

        employeeDao.create(employee2);
        employeeDao.create(employee);
    }


    @Test
    public void testGetEmployee() {
        Assert.assertNotNull(employeeDao.findById(userName));
    }

    @Test
    public void testRemoveEmployee() {
        employeeDao.delete(employee);
        Assert.assertNull(employeeDao.findById(userName));
    }

    @Test
    public void testFindAll() {
        Assert.assertEquals(2, employeeDao.findAll().size());
    }

    @Test
    public void testUpdateEmployee() {
        Employee newEmployee = new Employee();
        newEmployee.setEmail("femail@f.com");
        newEmployee.setFirstName("Pavel");
        newEmployee.setLastName("Smith");
        newEmployee.setUsername(userName);
        newEmployee.setPassword("1234");
        
        employeeDao.update(newEmployee);
        Employee result = employeeDao.findById(userName2);
        Assert.assertEquals("Smith", result.getLastName());
    }
    
    @Test
    public void testSearchByName(){
        Assert.assertNotNull(employeeDao.findByName("Pavel", null));
    }
}
