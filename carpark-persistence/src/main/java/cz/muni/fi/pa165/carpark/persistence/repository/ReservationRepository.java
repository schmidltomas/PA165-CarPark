package cz.muni.fi.pa165.carpark.persistence.repository;

import cz.muni.fi.pa165.carpark.persistence.dao.ReservationDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Reservation DAO implementation
 *
 * @author Robert Taptik
 */

@Repository
public class ReservationRepository implements ReservationDao {

    private final String ALL_RESERVATIONS_QUERY = "SELECT reservation FROM RESERVATION reservation";

    @PersistenceContext
    private EntityManager entityManager;

    public long create(Reservation reservation) {
        entityManager.persist(reservation);
        return reservation.getId();
    }

    public Reservation findById(long id) {
        return entityManager.find(Reservation.class, id);
    }

    public void update(Reservation reservation) {
        entityManager.merge(reservation);
    }

    public void delete(Reservation reservation) {
        entityManager.remove(findById(reservation.getId()));
    }

    public List<Reservation> getAll() {
        return entityManager.createQuery(ALL_RESERVATIONS_QUERY, Reservation.class).getResultList();
    }
}
