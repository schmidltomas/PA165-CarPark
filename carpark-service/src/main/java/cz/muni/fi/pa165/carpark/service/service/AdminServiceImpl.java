package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.dao.AdminDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tomáš Schmidl
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public void create(Admin admin) {
        adminDao.create(admin);
    }

    @Override
    public void update(Admin admin) {
        adminDao.update(admin);
    }

    @Override
    public void delete(Admin admin) {
        adminDao.delete(admin);
    }

    @Override
    public Admin findById(Long id) {
        return adminDao.findById(id);
    }

    @Override
    public List<Admin> findByName(String firstName, String secondName) {
        return adminDao.findByName(firstName, secondName);
    }

    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }
}
