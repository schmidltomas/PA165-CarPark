package cz.muni.fi.pa165.carpark.api.facade;

import cz.muni.fi.pa165.carpark.api.dto.UserDTO;

/**
 * @author Tomáš Schmidl
 */
public interface UserFacade {

    void register(UserDTO userDTO);

    boolean authenticate(UserDTO userDTO);

}
