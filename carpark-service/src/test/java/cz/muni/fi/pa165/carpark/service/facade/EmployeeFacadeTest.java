package cz.muni.fi.pa165.carpark.service.facade;

import cz.muni.fi.pa165.carpark.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carpark.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.carpark.service.service.EmployeeService;
import cz.muni.fi.pa165.carpark.service.utils.mapper.ClassMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 *
 * @author Jakub Kriz
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EmployeeFacadeTest extends AbstractTransactionalTestNGSpringContextTests{
    
    @Mock
    private EmployeeService employeeService;

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private ClassMapper classMapper;

    @InjectMocks
    private EmployeeFacadeImpl employeeFacade;

    private EmployeeDTO employeeDTO;

    private EmployeeDTO employeeDTO2;

    private Employee employee;

    private Employee employee2;
    
    @Before
    public void createEmployees(){
        employee = new Employee();
        employee.setEmail("JFK@email.com");
        employee.setFirstName("John");
        employee.setSecondName("Kennedy");
        employee.setUsername("JFK");
        employee.setPassword("strongPass");
        employee.setId(new Long(1));
        
        employeeDTO = new EmployeeDTO();
        employeeDTO.setEmail("JFK@email.com");
        employeeDTO.setFirstName("John");
        employeeDTO.setSecondName("Kennedy");
        employeeDTO.setUsername("JFK");
        employeeDTO.setPassword("strongPass");
        employeeDTO.setId(new Long(1));
        
        employee2 = new Employee();
        employee2.setEmail("MR@nobody.com");
        employee2.setFirstName("Mario");
        employee2.setSecondName("Roman");
        employee2.setUsername("Nobody");
        employee2.setPassword("noPass");
        employee2.setId(new Long(2));
        
        employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setEmail("MR@nobody.com");
        employeeDTO2.setFirstName("Mario");
        employeeDTO2.setSecondName("Roman");
        employeeDTO2.setUsername("Nobody");
        employeeDTO2.setPassword("noPass");
        employeeDTO2.setId(new Long(2));
        
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void findByIdTest() {
        when(employeeService.findById(anyLong())).thenReturn(employee);
        when(classMapper.mapTo(any(Employee.class), eq(EmployeeDTO.class))).thenReturn(employeeDTO);
        
        final EmployeeDTO empl = employeeFacade.findById(employee.getId());
        Assert.assertNotNull(empl);
        Assert.assertEquals(empl.getId(), employee.getId());
        Assert.assertEquals(empl.getEmail(), employee.getEmail());
    }
    
    public void findAllTest(){
        when(employeeService.findAllEmployees()).thenReturn(Arrays.asList(employee, employee2));
        when(classMapper.mapTo(anyCollectionOf(Employee.class), eq(EmployeeDTO.class))).thenReturn(Arrays.asList(employeeDTO, employeeDTO2));
    
        final List<EmployeeDTO> employees = (List<EmployeeDTO>) employeeFacade.findAllEmployees();
        Assert.assertNotNull(employees);
        Assert.assertEquals(2, employees.size());
    }
    
    public void findByNameTest(){
        when(employeeService.findByName(employee.getFirstName(), employee.getSecondName())).thenReturn(Arrays.asList(employee));
        when(classMapper.mapTo(anyCollectionOf(Employee.class), eq(EmployeeDTO.class))).thenReturn(Arrays.asList(employeeDTO));
        
        final List<EmployeeDTO> employees = (List<EmployeeDTO>) employeeFacade.findByName("", "Kennedy");
        Assert.assertNotNull(employees);
        Assert.assertEquals(1, employees.size());
    }
    
    public void updateEmployeeTest(){
        when(classMapper.mapTo(any(EmployeeDTO.class), eq(Employee.class))).thenReturn(employee);
        employeeFacade.update(employeeDTO);
        verify(employeeService).updateEmployee(employee);
    }
    
    public void removeEmployeeTest(){
        when(classMapper.mapTo(any(EmployeeDTO.class), eq(Employee.class))).thenReturn(employee);
        employeeFacade.delete(employeeDTO.getId());
        verify(employeeService).deleteEmployee(employee);
    }
}
