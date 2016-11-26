package cz.muni.fi.pa165.carpark.service.facade;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cz.muni.fi.pa165.carpark.api.dto.AdminDTO;
import cz.muni.fi.pa165.carpark.persistence.dao.AdminDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Admin;
import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.carpark.service.service.AdminService;
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
import java.util.List;

/**
 * @author Tomáš Schmidl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AdminFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private AdminService adminService;

    @Mock
    private AdminDao adminDao;

    @Mock
    private ClassMapper classMapper;

    @InjectMocks
    private AdminFacadeImpl adminFacade;

    private AdminDTO adminDTO1;
    private AdminDTO adminDTO2;
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

        adminDTO1 = new AdminDTO();
        adminDTO1.setId(Integer.toUnsignedLong(1));
        adminDTO1.setEmail(email1);
        adminDTO1.setEnabled(enabled1);
        adminDTO1.setFirstName(firstName1);
        adminDTO1.setSecondName(lastName1);
        adminDTO1.setUsername(username1);
        adminDTO1.setPassword(password1);

        admin2 = new Admin();
        admin2.setId(Integer.toUnsignedLong(2));
        admin2.setEmail(email2);
        admin2.setEnabled(enabled2);
        admin2.setFirstName(firstName2);
        admin2.setSecondName(lastName2);
        admin2.setUsername(username2);
        admin2.setPassword(password2);

        adminDTO2 = new AdminDTO();
        adminDTO2.setId(Integer.toUnsignedLong(2));
        adminDTO2.setEmail(email2);
        adminDTO2.setEnabled(enabled2);
        adminDTO2.setFirstName(firstName2);
        adminDTO2.setSecondName(lastName2);
        adminDTO2.setUsername(username2);
        adminDTO2.setPassword(password2);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdTest() {
        when(adminService.findById(anyLong())).thenReturn(admin1);
        when(classMapper.mapTo(any(Admin.class), eq(AdminDTO.class))).thenReturn(adminDTO1);

        final AdminDTO adm = adminFacade.findById(admin1.getId());
        Assert.assertNotNull(adm);
        Assert.assertEquals(adm.getId(), admin1.getId());
        Assert.assertEquals(adm.getUsername(), admin1.getUsername());
        Assert.assertEquals(adm.getEmail(), admin1.getEmail());
    }

    @Test
    public void findAllTest() {
        when(adminService.findAll()).thenReturn(Arrays.asList(admin1, admin2));
        when(classMapper.mapTo(anyCollectionOf(Admin.class), eq(AdminDTO.class)))
                .thenReturn(Arrays.asList(adminDTO1, adminDTO2));

        final List<AdminDTO> admins = adminFacade.findAll();
        Assert.assertNotNull(admins);
        Assert.assertEquals(2, admins.size());
    }

    @Test
    public void findByNameTest() {
        when(adminService.findByName(admin1.getFirstName(), admin2.getSecondName())).thenReturn(Arrays.asList(admin1));
        when(classMapper.mapTo(anyCollectionOf(Admin.class), eq(AdminDTO.class))).thenReturn(Arrays.asList(adminDTO1));

        final List<AdminDTO> admins = adminFacade.findByName(admin1.getFirstName(), admin1.getSecondName());
        Assert.assertNotNull(admins);
        Assert.assertEquals(1, admins.size());
    }

    @Test
    public void updateAdminTest() {
        when(classMapper.mapTo(any(AdminDTO.class), eq(Admin.class))).thenReturn(admin1);
        adminFacade.update(adminDTO1);
        verify(adminService).update(admin1);
    }

    @Test
    public void removeAdminTest() {
        when(classMapper.mapTo(any(AdminDTO.class), eq(Admin.class))).thenReturn(admin1);
        adminFacade.delete(adminDTO1);
        verify(adminService).delete(admin1);
    }
}
