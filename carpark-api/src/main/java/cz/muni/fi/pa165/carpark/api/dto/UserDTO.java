/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.api.dto;

/**
 *
 * @Author Tomáš Schmidl
 */
public class UserDTO {
    
    protected long id;

    protected String username;
    
    protected String password;
    
    protected String email;
    
    protected String firstName;
    
    protected String secondName;
    
    protected Boolean enabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
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
                 ", enabled=" + enabled +
                 ", firstName='" + firstName + '\'' +
                 ", secondName='" + secondName + '\'' +
                 '}';
     }
}
