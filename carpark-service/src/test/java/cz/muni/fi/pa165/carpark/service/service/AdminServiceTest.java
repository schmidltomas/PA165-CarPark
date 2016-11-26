package cz.muni.fi.pa165.carpark.service.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cz.muni.fi.pa165.carpark.persistence.dao.AdminDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Admin;
import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tomáš Schmidl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AdminServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    AdminDao adminDao;

    @InjectMocks
    AdminServiceImpl adminService;

    private Admin admin1;
    private Admin admin2;

    private static final String email1 = "martin@company.com";
    private static final boolean enabled1 = true;
    private static final String firstName1 = "Martin";
    private static final String lastName1 = "Admin";
    private static final String username1 = "root";
    private static final String password1 = "admin";

    private static final String email2 = "roman@company.com";
    private static final boolean enabled2 = true;
    private static final String firstName2 = "Roman";
    private static final String lastName2 = "Adminovic";
    private static final String username2 = "roman";
    private static final String password2 = "54321";

    @Before
    public void setup() {
        admin1 = new Admin();
        admin1.setId(Integer.toUnsignedLong(1));
        admin1.setEmail(email1);
        admin1.setEnabled(enabled1);
        admin1.setFirstName(firstName1);
        admin1.setSecondName(lastName1);
        admin1.setUsername(username1);
        admin1.setPassword(password1);

        admin2 = new Admin();
        admin1.setId(Integer.toUnsignedLong(2));
        admin2.setEmail(email2);
        admin2.setEnabled(enabled2);
        admin2.setFirstName(firstName2);
        admin2.setSecondName(lastName2);
        admin2.setUsername(username2);
        admin2.setPassword(password2);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createTest() {
        adminService.create(admin1);
        verify(adminDao).create(any(Admin.class));
    }

    @Test
    public void updateTest() {
        adminService.update(admin1);
        verify(adminDao).update(any(Admin.class));
    }

    @Test
    public void deleteTest() {
        adminService.delete(admin1);
        verify(adminDao).delete(any(Admin.class));
    }

    @Test
    public void findByIdTest() {
        when(adminService.findById(anyLong())).thenReturn(admin1);
        final Admin result = adminService.findById(admin1.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(admin1.getId(), result.getId());
    }

    @Test
    public void findByNameTest() {
        when(adminService.findByName(anyString(), anyString())).thenReturn(Arrays.asList(admin1));
        final List<Admin> result = adminService.findByName("", admin1.getSecondName());
        Assert.assertNotNull(result);
        Assert.assertEquals(admin1.getId(), result.get(0).getId());
    }

    @Test
    public void findAllTest() {
        when(adminService.findAll()).thenReturn(Arrays.asList(admin1, admin2));
        final List<Admin> result = adminService.findAll();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() == 2);
    }

}
