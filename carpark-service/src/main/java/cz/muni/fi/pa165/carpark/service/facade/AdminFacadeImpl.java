package cz.muni.fi.pa165.carpark.service.facade;

import cz.muni.fi.pa165.carpark.api.dto.AdminDTO;
import cz.muni.fi.pa165.carpark.api.dto.UserDTO;
import cz.muni.fi.pa165.carpark.api.facade.AdminFacade;
import cz.muni.fi.pa165.carpark.persistence.entity.Admin;
import cz.muni.fi.pa165.carpark.service.service.AdminService;
import cz.muni.fi.pa165.carpark.service.utils.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminFacadeImpl implements AdminFacade {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private AdminService adminService;

    @Override
    public void create(AdminDTO adminDTO) {
        Admin admin = classMapper.mapTo(adminDTO, Admin.class);
        adminService.create(admin);
    }

    @Override
    public void update(AdminDTO adminDTO) {
        Admin admin = classMapper.mapTo(adminDTO, Admin.class);
        adminService.update(admin);
    }

    @Override
    public void delete(AdminDTO adminDTO) {
        Admin admin = classMapper.mapTo(adminDTO, Admin.class);
        adminService.delete(admin);
    }

    @Override
    public AdminDTO findById(Long id) {
        Admin admin = adminService.findById(id);
        return admin == null ? null : classMapper.mapTo(admin, AdminDTO.class);
    }

    @Override
    public AdminDTO findByName(String firstName, String secondName) {
        Admin admin = adminService.findByName(firstName, secondName);
        return admin == null ? null : classMapper.mapTo(admin, AdminDTO.class);
    }

    @Override
    public List<AdminDTO> findAll() {
        List<Admin> admins = adminService.findAll();
        return classMapper.mapTo(admins, AdminDTO.class);
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
