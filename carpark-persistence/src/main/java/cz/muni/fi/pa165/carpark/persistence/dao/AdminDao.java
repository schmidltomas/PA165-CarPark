package cz.muni.fi.pa165.carpark.persistence.dao;

import cz.muni.fi.pa165.carpark.persistence.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Administrator Data Access Object interface.
 *
 * @Author Tomáš Schmidl
 */
@Service
public interface AdminDao {

    /**
     * Persist a new administrator in the database.
     *
     * @param admin Administrator to persist.
     */
    void create(Admin admin);

    /**
     * Update an existing administrator in the database.
     *
     * @param admin Administrator to update.
     */
    void update(Admin admin);

    /**
     * Delete an administrator from the database.
     *
     * @param admin Administrator to delete.
     */
    void delete(Admin admin);

    /**
     * Find and return an administrator based on his ID.
     *
     * @param id Valid ID of an administrator.
     * @return Administrator object if found, null if not found.
     */
    Admin findById(Long id);

    /**
     * Find and return an administrator based on his first and last name.
     *
     * @param firstName Administrator's first name.
     * @param secondName Administrator's second name.
     * @return Administrator object if found, null if not found.
     */
    Admin findByName(String firstName, String secondName);

    /**
     * Return a list of all Administrators.
     * @return List with Administrator object, empty list if there are no Administrators.
     */
    List<Admin> findAll();
}
