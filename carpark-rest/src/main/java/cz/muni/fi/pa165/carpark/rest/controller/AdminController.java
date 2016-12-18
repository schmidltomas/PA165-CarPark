package cz.muni.fi.pa165.carpark.rest.controller;

import cz.muni.fi.pa165.carpark.api.dto.AdminDTO;
import cz.muni.fi.pa165.carpark.api.facade.AdminFacade;
import cz.muni.fi.pa165.carpark.rest.utils.ControllerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Controller
@RequestMapping(value = "/pa165/rest/admin/")
public class AdminController {

    @Autowired
    private AdminFacade adminFacade;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity createAdmin(@Valid @RequestBody AdminDTO adminDTO) throws Exception {
        return ControllerResponse.processResponse(adminFacade.create(adminDTO));
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity updateAdmin(@Valid @RequestBody AdminDTO adminDTO) throws Exception {
        if (adminDTO.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Field ID is missing.");
        }
        final AdminDTO adminToUpdate = adminFacade.findById(adminDTO.getId());
        if (adminToUpdate != null) {
            return ControllerResponse.processResponse(adminFacade.update(adminToUpdate));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAdmin(@Min(0) @PathVariable Long id) {
        if(id != null) {
            adminFacade.delete(id);
            return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @RequestMapping(value = "getById", method = RequestMethod.GET, params = "id")
    public ResponseEntity getAdminById(@Min(0) @RequestParam("id") Long id) throws Exception {
        return ControllerResponse.processResponse(adminFacade.findById(id));
    }

    @RequestMapping(value = "getByName", method = RequestMethod.GET, params = {"firstName", "lastName"})
    public ResponseEntity getAdminByName(@Min(0) @RequestParam("firstName") String firstName,
                                         @Min(0) @RequestParam("lastName") String lastName) throws Exception {
        return ControllerResponse.processResponse(adminFacade.findByName(firstName, lastName));
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public ResponseEntity getAllAdmins() {
        return ResponseEntity.ok(adminFacade.findAll());
    }
}
