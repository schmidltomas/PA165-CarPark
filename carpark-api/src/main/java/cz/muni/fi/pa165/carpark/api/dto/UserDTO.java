/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @Author Tomáš Schmidl
 */
public class UserDTO {

    @JsonProperty
    protected Long id;

    @JsonProperty
    @NotBlank
    protected String username;

    @JsonProperty
    @NotBlank
    protected String password;

    @JsonProperty
    @NotBlank
    protected String email;

    @JsonProperty
    @NotBlank
    protected String firstName;

    @JsonProperty
    @NotBlank
    protected String secondName;

    @JsonProperty
    protected String userRole;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    
    @Override
     public String toString() {
         return "UserDTO{" +
                 "id=" + id +
                 ", username='" + username + '\'' +
                 ", password='" + password + '\'' +
                 ", email='" + email + '\'' +
                 ", firstName='" + firstName + '\'' +
                 ", secondName='" + secondName + '\'' +
                 '}';
     }
}
