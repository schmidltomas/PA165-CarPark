package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;

import java.util.Date;
import java.util.List;

/**
 * Interface for managing reservations in Car Park.
 *
 * @author Robert Taptik
 */
public interface ReservationDAO {

    /** Creates a reservation entity in the database.
     *
     * @param reservation to be created, must not be null
     *
     */
    long create(Reservation reservation);

    /**
     * Finds a reservation entity in the database by id.
     *
     * @param id, must not be null
     * @return Reservation object or null if not found
     */
    Reservation findById(Long id);

    /**
     * Updates a reservation entity in the database.
     *
     * @param reservation to be updated, must not be null
     */
    void update(Reservation reservation);

    /**
     * Deletes a reservation entity in the database.
     *
     * @param reservation to be deleted, must not be null
     */
    void delete(Reservation reservation);

    /**
     * Lists all the reservations in the database.
     *
     * @return List of all the reservation entities in the database
     */
    List<Reservation> getAll();

    /**
     * Lists all reservations for a given employee
     *
     * @return List of all reservations for a given employee
     */
    List<Reservation> getReservations(Employee employee);
    
    List<Reservation> getReservations(Date startDate, Date endDate);
}
