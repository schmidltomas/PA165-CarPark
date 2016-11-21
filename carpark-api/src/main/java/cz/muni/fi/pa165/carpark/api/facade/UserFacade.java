/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.api.facade;

import cz.muni.fi.pa165.carpark.api.dto.UserDTO;

/**
 *
 * @Author Tomáš Schmidl
 */
public interface UserFacade {
    void register(UserDTO userDTO);
    boolean authenticate(UserDTO userDTO);
}
