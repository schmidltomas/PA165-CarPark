package cz.muni.fi.pa165.carpark.api.facade;

import cz.muni.fi.pa165.carpark.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carpark.api.dto.ReservationDTO;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;

import java.util.List;

/**
 * Created by robot on 25.11.16.
 */
public interface ReservationFacade {

    ReservationDTO create(ReservationDTO reservation);

    ReservationDTO findById(Long id);

    ReservationDTO update(ReservationDTO reservation);

    void delete(ReservationDTO reservation);

    List<ReservationDTO> getAll();

    List<ReservationDTO> getReservations(EmployeeDTO employee);
}
