package cz.muni.fi.pa165.carpark.api.facade;

import cz.muni.fi.pa165.carpark.api.dto.AdminDTO;

import java.util.List;

/**
 * @author Tomáš Schmidl
 */
public interface AdminFacade extends UserFacade {

    void create(AdminDTO adminDTO);

    void update(AdminDTO adminDTO);

    void delete(AdminDTO adminDTO);

    AdminDTO findById(Long id);

    List<AdminDTO> findByName(String firstName, String secondName);

    List<AdminDTO> findAll();
}
