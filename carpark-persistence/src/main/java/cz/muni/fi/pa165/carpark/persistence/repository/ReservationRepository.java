package cz.muni.fi.pa165.carpark.persistence.repository;

import cz.muni.fi.pa165.carpark.persistence.dao.ReservationDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Reservation DAO implementation
 *
 * @author Robert Taptik
 */

@Repository
@Transactional
public class ReservationRepository implements ReservationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Reservation reservation) { entityManager.persist(reservation); }

    @Override
    public Reservation findById(Long id) {
        return entityManager.find(Reservation.class, id);
    }

    @Override
    public void update(Reservation reservation) {
        entityManager.merge(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        entityManager.remove(findById(reservation.getId()));
    }

    public List<Reservation> getAll() {
        return entityManager.createQuery("SELECT r FROM reservation r", Reservation.class).getResultList();
    }


}
