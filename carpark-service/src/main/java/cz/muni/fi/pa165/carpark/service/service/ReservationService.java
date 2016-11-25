package cz.muni.fi.pa165.carpark.service.service;

import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;

import java.util.List;

/**
 * Created by robot on 25.11.16.
 */
public interface ReservationService {

    /** Creates a reservation entity in the database.
     *
     * @param reservation to be created, must not be null
     *
     */
    void create(Reservation reservation);
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
}
