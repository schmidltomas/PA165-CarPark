/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.persistence.repository;

import cz.muni.fi.pa165.carpark.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jakub Kříž
 */

@Repository
@Transactional
public class EmployeeRepository implements EmployeeDao{

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
    public List<Employee> findAll() {
        return entityManager.createQuery("SELECT e FROM employee e", Employee.class).getResultList();
    }

    @Override
    public List<Employee> findByName(String firstName, String secoundName) {
        firstName = firstName == null ? "" : firstName;
        secoundName = secoundName == null ? "" : secoundName;
        return entityManager.createQuery("SELECT e FROM employee e "
                + "WHERE first_name LIKE :first_name "
                + "AND secound_name LIKE :secound_name", Employee.class)
                .setParameter("first_name", firstName)
                .setParameter("secound_name", secoundName).getResultList();
    }
    
}
