package cz.muni.fi.pa165.carpark.service.facade;

import cz.muni.fi.pa165.carpark.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carpark.api.dto.ReservationDTO;
import cz.muni.fi.pa165.carpark.api.facade.ReservationFacade;
import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import cz.muni.fi.pa165.carpark.service.service.EmployeeService;
import cz.muni.fi.pa165.carpark.service.service.ReservationService;
import cz.muni.fi.pa165.carpark.service.utils.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by robot on 25.11.16.
 */
@Service
@Transactional
public class ReservationFacadeImpl implements ReservationFacade {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void create(ReservationDTO reservationDTO) {
        reservationService.create(classMapper.mapTo(reservationDTO, Reservation.class));
    }

    @Override
    public ReservationDTO findById(Long id) {
        final Reservation res = reservationService.findById(id);
        return res == null ? null : classMapper.mapTo(res, ReservationDTO.class);
    }

    @Override
    public void update(ReservationDTO reservation) {
        reservationService.update(classMapper.mapTo(reservation, Reservation.class));
    }

    @Override
    public void delete(ReservationDTO reservation) {
        reservationService.delete(classMapper.mapTo(reservation, Reservation.class));
    }

    @Override
    public List<ReservationDTO> getAll() {
        return classMapper.mapTo(reservationService.getAll(), ReservationDTO.class);
    }

    @Override
    public List<ReservationDTO> getReservations(EmployeeDTO employee) {
        return classMapper.mapTo(reservationService.getReservations(employeeService.findById(employee.getId())),
               ReservationDTO.class);
    }
}
