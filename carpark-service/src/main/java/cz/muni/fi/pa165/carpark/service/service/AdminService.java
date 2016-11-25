package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.entity.Admin;

import java.util.List;

public interface AdminService {

    void create(Admin admin);

    void update(Admin admin);

    void delete(Admin admin);

    Admin findById(Long id);

    Admin findByName(String firstName, String secondName);

    List<Admin> findAll();
}
