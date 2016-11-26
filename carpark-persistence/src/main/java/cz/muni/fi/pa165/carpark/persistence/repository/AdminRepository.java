package cz.muni.fi.pa165.carpark.persistence.repository;

import cz.muni.fi.pa165.carpark.persistence.dao.AdminDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Admin;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class AdminRepository implements AdminDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Admin admin) {
        entityManager.persist(admin);
    }

    @Override
    public void update(Admin admin) {
        entityManager.merge(admin);
    }

    @Override
    public void delete(Admin admin) {
        entityManager.remove(admin);
    }

    @Override
    public Admin findById(Long id) {
        return entityManager.find(Admin.class, id);
    }

    @Override
    public List<Admin> findByName(String firstName, String secondName) {
        firstName = firstName == null ? "" : firstName;
        secondName = secondName == null ? "" : secondName;
        return entityManager.createQuery("SELECT a FROM Admin a "
                + "WHERE first_name LIKE :first_name "
                + "AND second_name LIKE :second_name", Admin.class)
                .setParameter("first_name", firstName)
                .setParameter("second_name", secondName).getResultList();
    }

    @Override
    public List<Admin> findAll() {
        return entityManager.createQuery("SELECT a FROM Admin a", Admin.class).getResultList();
    }
}
