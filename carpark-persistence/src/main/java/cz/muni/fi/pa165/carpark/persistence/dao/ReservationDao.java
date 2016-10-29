package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;

import java.util.List;

/**
 * Interface for managing reservations in Car Park.
 *
 * @author Robert Taptik
 */
public interface ReservationDao {

    /** Creates a reservation entity in the database.
     *
     * @param reservation to be created, must not be null
     * @return long reservation id
     */
    long create(Reservation reservation);

    /**
     * Finds a reservation entity in the database by id.
     *
     * @param id, must not be null
     * @return Reservation object or null if not found
     */
    Reservation findById(long id);

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
}
