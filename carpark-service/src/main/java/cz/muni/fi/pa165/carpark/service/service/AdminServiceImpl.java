package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.dao.AdminDAO;
import cz.muni.fi.pa165.carpark.persistence.entity.Admin;
import cz.muni.fi.pa165.carpark.persistence.entity.UserRole;
import cz.muni.fi.pa165.carpark.service.utils.AuthnResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tomáš Schmidl
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDAO adminDAO;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public void create(Admin admin) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        admin.setUserRole(UserRole.ROLE_ADMIN);
        adminDAO.create(admin);
    }

    @Override
    public void update(Admin admin) {
        adminDAO.update(admin);
    }

    @Override
    public void delete(Admin admin) {
        adminDAO.delete(admin);
    }

    @Override
    public Admin findById(Long id) {
        return adminDAO.findById(id);
    }

    @Override
    public List<Admin> findByName(String firstName, String secondName) {
        return adminDAO.findByName(firstName, secondName);
    }

    @Override
    public List<Admin> findAll() {
        return adminDAO.findAll();
    }

    @Override
    public AuthnResponse authenticate(String email, String password) {
        Admin admin = adminDAO.findByEmail(email);

        if (admin == null) {
            return AuthnResponse.NOT_FOUND;
        } else {
            String encodedPassword = admin.getPassword();
            if (!bCryptPasswordEncoder.matches(password, encodedPassword)) {
                return AuthnResponse.INCORRECT_PASSWORD;
            }
        }

        return AuthnResponse.OK;
    }
}
