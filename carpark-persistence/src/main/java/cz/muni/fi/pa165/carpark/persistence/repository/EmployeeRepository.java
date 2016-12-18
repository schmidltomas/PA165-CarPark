/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.persistence.repository;

import cz.muni.fi.pa165.carpark.persistence.dao.EmployeeDAO;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jakub Kříž
 */

@Repository
@Transactional
public class EmployeeRepository implements EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void create(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public void update(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    public void delete(Employee employee) {
        entityManager.remove(employee);
    }

    @Override
    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT e FROM Employee e WHERE e.email = :email", Employee.class)
                    .setParameter("email", email).getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Employee> findAll() {
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    @Override
    public List<Employee> findByName(String firstName, String secondName) {
        firstName = firstName == null ? "" : firstName;
        secondName = secondName == null ? "" : secondName;
        return entityManager.createQuery("SELECT e FROM Employee e "
                + "WHERE first_name LIKE :first_name "
                + "AND second_name LIKE :second_name", Employee.class)
                .setParameter("first_name", firstName)
                .setParameter("second_name", secondName).getResultList();
    }
    
}
