package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.entity.Admin;
import cz.muni.fi.pa165.carpark.service.utils.AuthnResponse;

import java.util.List;

/**
 * @author Tomáš Schmidl
 */
public interface AdminService {

    void create(Admin admin);

    void update(Admin admin);

    void delete(Admin admin);

    Admin findById(Long id);

    List<Admin> findByName(String firstName, String secondName);

    List<Admin> findAll();

    AuthnResponse authenticate(String email, String password);
}
