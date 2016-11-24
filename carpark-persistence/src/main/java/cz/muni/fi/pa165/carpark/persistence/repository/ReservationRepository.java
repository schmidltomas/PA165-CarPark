package cz.muni.fi.pa165.carpark.persistence.repository;

import cz.muni.fi.pa165.carpark.persistence.dao.ReservationDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import java.sql.Date;
import java.util.Calendar;
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
    public long create(Reservation reservation) { 
        entityManager.persist(reservation); 
        return reservation.getId();
    }

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
        return entityManager.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
    }

    @Override
    public List<Reservation> getReservations(Employee employee){
        return entityManager.createQuery("SELECT r FROM Reservation r WHERE employee_id=:employee_id", Reservation.class).
                setParameter("employee_id", employee.getId()).getResultList();
    }

    @Override
    public List<Reservation> getReservations(Date startDate, Date endDate) {
        Calendar endcal = Calendar.getInstance();
        endcal.setTime(endDate);
        Calendar startcal = Calendar.getInstance();
        endcal.setTime(startDate);
        return entityManager.createQuery("SELECT r FROM Reservation r WHERE" 
            + "(start_date<:end_date AND end_date>:start_date", Reservation.class).
            setParameter("start_date", startcal).setParameter("end_date", endcal).getResultList();
    }
}
