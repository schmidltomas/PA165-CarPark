package cz.muni.fi.pa165.carpark.api.dto;

/**
 * @Author Tomáš Schmidl
 */
public class AdminDTO extends UserDTO {

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
