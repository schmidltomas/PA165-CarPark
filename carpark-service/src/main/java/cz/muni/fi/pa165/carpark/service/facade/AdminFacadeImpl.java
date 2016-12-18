package cz.muni.fi.pa165.carpark.service.facade;

import cz.muni.fi.pa165.carpark.api.dto.AdminDTO;
import cz.muni.fi.pa165.carpark.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carpark.api.dto.UserDTO;
import cz.muni.fi.pa165.carpark.api.facade.AdminFacade;
import cz.muni.fi.pa165.carpark.persistence.entity.Admin;
import cz.muni.fi.pa165.carpark.persistence.entity.Employee;
import cz.muni.fi.pa165.carpark.service.service.AdminService;
import cz.muni.fi.pa165.carpark.service.utils.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Tomáš Schmidl
 */
@Service
@Transactional
public class AdminFacadeImpl implements AdminFacade {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private AdminService adminService;

    @Override
    public AdminDTO create(AdminDTO adminDTO) {
        final Admin admin = classMapper.mapTo(adminDTO, Admin.class);
        adminService.create(admin);
        return classMapper.mapTo(admin, AdminDTO.class);
    }

    @Override
    public AdminDTO update(AdminDTO adminDTO) {
        Admin admin = classMapper.mapTo(adminDTO, Admin.class);
        adminService.update(admin);
        return classMapper.mapTo(admin, AdminDTO.class);
    }

    @Override
    public AdminDTO delete(Long id) {
        final Admin adminToRemove = adminService.findById(id);
        if (adminToRemove != null) {
            adminService.delete(adminToRemove);
            return classMapper.mapTo(adminToRemove, AdminDTO.class);
        }
        return null;
    }

    @Override
    public AdminDTO findById(Long id) {
        Admin admin = adminService.findById(id);
        return admin == null ? null : classMapper.mapTo(admin, AdminDTO.class);
    }

    @Override
    public List<AdminDTO> findByName(String firstName, String secondName) {
        List<Admin> admin = adminService.findByName(firstName, secondName);
        return admin == null ? null : classMapper.mapTo(admin, AdminDTO.class);
    }

    @Override
    public List<AdminDTO> findAll() {
        final List<Admin> admins = adminService.findAll();
        List<AdminDTO> adminDTOs = classMapper.mapTo(admins, AdminDTO.class);
        return adminDTOs;
    }

    @Override
    public void register(UserDTO userDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean authenticate(UserDTO userDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
