package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.configuration.PersistanceConfiguration;
import cz.muni.fi.pa165.carpark.persistence.entity.Admin;
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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersistanceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AdminDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AdminDao adminDao;

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

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void createAdmin() {
        admin1 = new Admin();
        admin1.setEmail(email1);
        admin1.setEnabled(enabled1);
        admin1.setFirstName(firstName1);
        admin1.setLastName(lastName1);
        admin1.setUsername(username1);
        admin1.setPassword(password1);

        admin2 = new Admin();
        admin2.setEmail(email2);
        admin2.setEnabled(enabled2);
        admin2.setFirstName(firstName2);
        admin2.setLastName(lastName2);
        admin2.setUsername(username2);
        admin2.setPassword(password2);

        adminDao.create(admin1);
        adminDao.create(admin2);
    }

    @Test
    public void testGetAdminById() {
        Admin persistedAdmin = adminDao.findById(username1);
        Assert.assertNotNull(persistedAdmin);
        Assert.assertTrue(adminEquals(persistedAdmin, admin1));
    }

    @Test
    public void testGetAdminByName() {
        Admin persistedAdmin = adminDao.findByName(firstName1, lastName1);
        Assert.assertNotNull(persistedAdmin);
        Assert.assertTrue(adminEquals(persistedAdmin, admin1));
    }

    @Test
    public void testFindAllAdmins(){
        List<Admin> admins  = adminDao.findAll();
        Assert.assertEquals(admins.size(), 2);
        Assert.assertTrue(admins.contains(admin1));
        Assert.assertTrue(admins.contains(admin2));
    }

    @Test
    public void testUpdateAdmin(){
        Admin oldAdmin = adminDao.findById(username1);

        oldAdmin.setEmail("martin.new@company.com");
        oldAdmin.setEnabled(false);

        adminDao.update(oldAdmin);
        Admin updatedAdmin = adminDao.findById(username1);

        Assert.assertTrue(adminEquals(oldAdmin, updatedAdmin));
    }

    @Test
    public void testDeleteAdmin() {
        adminDao.delete(admin2);
    }

    public boolean adminEquals(Admin expected, Admin actual) {
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.isEnabled(), actual.isEnabled());
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getFirstName(), actual.getLastName());
        Assert.assertEquals(expected.getUsername(), actual.getUsername());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        return true;
    }
}
