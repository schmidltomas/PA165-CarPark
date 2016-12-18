package cz.muni.fi.pa165.carpark.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author Tomáš Schmidl
 */
public class LoginRequestDTO {

    @JsonProperty
    @NotBlank
    private String email;

    @JsonProperty
    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
