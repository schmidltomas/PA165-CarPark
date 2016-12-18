package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.configuration.PersistenceConfiguration;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub Kříž
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersistenceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class EmployeeDAOTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private EmployeeDAO employeeDAO;

    private Employee employee;
    private Employee employee2;
    private final String userName = "Pavel.Novak";
    private final String userName2 = "Jiri.Novotny";

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void createEmployee() {
        employee = new Employee();
        employee.setEmail("femail@f.com");
        employee.setFirstName("Pavel");
        employee.setSecondName("Novak");
        employee.setUsername(userName);
        employee.setPassword("1234");
        employee2 = new Employee();
        employee2.setEmail("femailjiri@f.com");
        employee2.setFirstName("Jiri");
        employee2.setSecondName("Novotny");
        employee2.setUsername(userName2);
        employee2.setPassword("321");

        employeeDAO.create(employee2);
        employeeDAO.create(employee);
    }


    @Test
    public void testGetEmployee() {
        Assert.assertNotNull(employeeDAO.findById(employee.getId()));
    }

    @Test
    public void testRemoveEmployee() {
        employeeDAO.delete(employee);
        Assert.assertNull(employeeDAO.findById(employee.getId()));
    }

    @Test
    public void testFindAll() {
        Assert.assertEquals(2, employeeDAO.findAll().size());
    }

    @Test
    public void testUpdateEmployee() {
        Employee newEmployee = new Employee();
        newEmployee.setEmail("femail@f.com");
        newEmployee.setFirstName("Pavel");
        newEmployee.setSecondName("Smith");
        newEmployee.setUsername(userName);
        newEmployee.setPassword("1234");
        newEmployee.setId(employee.getId());
        
        
        employeeDAO.update(newEmployee);
        Employee result = employeeDAO.findById(employee.getId());
        Assert.assertEquals("Smith", result.getSecondName());
    }
    
    @Test
    public void testSearchByName(){
        Assert.assertNotNull(employeeDAO.findByName("Pavel", null));
    }
}
