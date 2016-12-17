/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.api.dto;

import cz.muni.fi.pa165.carpark.persistence.entity.Reservation;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jakub Kriz
 */
public class EmployeeDTO extends UserDTO {

    private Set<Reservation> reservations = new HashSet<>();

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> Reservations) {
        this.reservations = Reservations;
    }
}
