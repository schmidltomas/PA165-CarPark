package cz.muni.fi.pa165.carpark.rest.controller;

import cz.muni.fi.pa165.carpark.api.dto.LoginRequestDTO;
import cz.muni.fi.pa165.carpark.api.dto.LoginResponseDTO;
import cz.muni.fi.pa165.carpark.api.facade.AdminFacade;
import cz.muni.fi.pa165.carpark.api.facade.EmployeeFacade;
import cz.muni.fi.pa165.carpark.rest.utils.ControllerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Tomáš Schmidl
 */
@Controller
@RequestMapping(value = "/pa165/rest/login")
public class LoginController {

    @Autowired
    private EmployeeFacade employeeFacade;

    @Autowired
    private AdminFacade adminFacade;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity sendRequest(@Valid @RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        LoginResponseDTO loginResponseDTO = employeeFacade.login(loginRequestDTO);

        if (loginResponseDTO.getUserRole() == null) {
            loginResponseDTO = adminFacade.login(loginRequestDTO);
        }

        return ControllerResponse.processResponse(loginResponseDTO);
    }
}
