package cz.muni.fi.pa165.carpark.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author Tomáš Schmidl
 */
public class LoginResponseDTO {

    @JsonProperty
    @NotBlank
    private boolean authenticated;

    @JsonProperty
    @NotBlank
    private String userRole;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
