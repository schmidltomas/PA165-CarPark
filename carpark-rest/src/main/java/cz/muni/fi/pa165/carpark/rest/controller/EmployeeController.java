/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.rest.controller;

import cz.muni.fi.pa165.carpark.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carpark.api.facade.EmployeeFacade;
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

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 *
 * @author Jakub Kriz
 */
@Controller
@RequestMapping(value = "/pa165/rest/employee/")
public class EmployeeController {
    
    @Autowired
    private EmployeeFacade employeeFacade;
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity createEmployee(@Valid @RequestBody EmployeeDTO request) throws Exception {
        return ControllerResponse.processResponse(employeeFacade.create(request));
    }
    
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity updateEmployee(@Valid @RequestBody EmployeeDTO request) throws Exception {
        if (request.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Field ID is missing.");
        }
        final EmployeeDTO employee = employeeFacade.findById(request.getId());
        if(employee == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
        return ControllerResponse.processResponse(employeeFacade.update(request));
    }
    
    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeEmployee(@Min(0) @PathVariable Long id){
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
        employeeFacade.delete(id);
        return ResponseEntity.ok("");
    }
    
    @RequestMapping(value = "getById", method = RequestMethod.GET, params = "id")
    public ResponseEntity getEmployeeById(@Min(0) @RequestParam("id") Long id) throws Exception {
        final EmployeeDTO employee = employeeFacade.findById(id);
        return ControllerResponse.processResponse(employee);
    }
    
    @RequestMapping(value = "getByName", method = RequestMethod.GET, params = {"name", "surName"})
    public ResponseEntity getEmployeeByName(String name, String surName) throws Exception {
        final Collection<EmployeeDTO> employee = employeeFacade.findByName(name, surName);
        return ControllerResponse.processResponse(employee);
    }
    
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public ResponseEntity getAllEmployees() throws Exception {
        final Collection<EmployeeDTO> employee = employeeFacade.findAllEmployees();
        return ControllerResponse.processResponse(employee);
    }
}
