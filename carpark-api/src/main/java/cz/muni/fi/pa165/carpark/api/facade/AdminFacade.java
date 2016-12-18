package cz.muni.fi.pa165.carpark.api.facade;

import cz.muni.fi.pa165.carpark.api.dto.AdminDTO;

import java.util.List;

/**
 * @author Tomáš Schmidl
 */
public interface AdminFacade extends UserFacade {

    AdminDTO create(AdminDTO adminDTO);

    AdminDTO update(AdminDTO adminDTO);

    AdminDTO delete(Long id);

    AdminDTO findById(Long id);

    List<AdminDTO> findByName(String firstName, String secondName);

    List<AdminDTO> findAll();
}
