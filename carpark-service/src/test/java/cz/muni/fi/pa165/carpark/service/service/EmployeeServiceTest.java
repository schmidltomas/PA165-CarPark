package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carpark.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Car;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.carpark.service.facade.EmployeeFacadeImpl;
import cz.muni.fi.pa165.carpark.service.utils.mapper.ClassMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 *
 * @author Jakub Kriz
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)

public class EmployeeServiceTest extends AbstractTestNGSpringContextTests{

    @Mock
    private EmployeeDao employeeDao;
    
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    
    private Employee employee;

    private Employee employee2;
    
    private Car car;

    @Before
    public void createEmployee() {
        employee = new Employee();
        employee.setEmail("JFK@email.com");
        employee.setFirstName("John");
        employee.setSecondName("Kennedy");
        employee.setUsername("JFK");
        employee.setPassword("strongPass");
        employee.setId(new Long(1));
        
        employee2 = new Employee();
        employee2.setEmail("MR@nobody.com");
        employee2.setFirstName("Mario");
        employee2.setSecondName("Roman");
        employee2.setUsername("Nobody");
        employee2.setPassword("noPass");
        employee2.setId(new Long(2));
        
        car = new Car();
        car.setId(Integer.toUnsignedLong(1));
        car.setBrand("bmw");
        car.setCurrentLocation("Brno");
        car.setEvidenceNumber("HGJ679");
        car.setFuelConsumption(5.6);
        car.setHomeLocation("Praha");
        car.setSeats(5);
        
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void MakeReservation(){
        
    }
    
}
