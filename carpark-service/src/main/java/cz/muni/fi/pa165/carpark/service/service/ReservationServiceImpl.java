package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.dao.ReservationDao;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by robot on 25.11.16.
 */
@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationDao reservationDao;

    @Override
    public void create(Reservation reservation) {
        reservationDao.create(reservation);
    }

    @Override
    public Reservation findById(Long id) {
        return reservationDao.findById(id);
    }

    @Override
    public void update(Reservation reservation) {
        reservationDao.update(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationDao.delete(reservation);
    }

    @Override
    public List<Reservation> getAll() {
        return reservationDao.getAll();
    }

    @Override
    public List<Reservation> getReservations(Employee employee) {
        return reservationDao.getReservations(employee);
    }


}
