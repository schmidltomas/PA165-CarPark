package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.configuration.PersistenceConfiguration;
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
@ContextConfiguration(classes=PersistenceConfiguration.class)
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
        admin1.setSecondName(lastName1);
        admin1.setUsername(username1);
        admin1.setPassword(password1);

        admin2 = new Admin();
        admin2.setEmail(email2);
        admin2.setEnabled(enabled2);
        admin2.setFirstName(firstName2);
        admin2.setSecondName(lastName2);
        admin2.setUsername(username2);
        admin2.setPassword(password2);

        adminDao.create(admin1);
        adminDao.create(admin2);
    }

    @Test
    public void testGetAdminById() {
        Admin persistedAdmin = adminDao.findById(admin1.getId());
        Assert.assertNotNull(persistedAdmin);
        adminEquals(persistedAdmin, admin1);
    }

    @Test
    public void testGetAdminByName() {
        List<Admin> persistedAdmin = adminDao.findByName(firstName1, lastName1);
        Assert.assertNotNull(persistedAdmin);
        adminEquals(persistedAdmin.get(0), admin1);
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
        Admin persistedAdmin = adminDao.findById(admin1.getId());

        persistedAdmin.setEmail("martin.new@company.com");
        persistedAdmin.setEnabled(false);
        adminDao.update(persistedAdmin);
        Admin updatedAdmin = adminDao.findById(persistedAdmin.getId());

        adminEquals(persistedAdmin, updatedAdmin);
    }

    @Test
    public void testDeleteAdmin() {
        adminDao.delete(admin2);
        Assert.assertNull(adminDao.findById(admin2.getId()));
    }

    public void adminEquals(Admin expected, Admin actual) {
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.isEnabled(), actual.isEnabled());
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getSecondName(), actual.getSecondName());
        Assert.assertEquals(expected.getUsername(), actual.getUsername());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
    }
}
