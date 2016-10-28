package cz.muni.fi.pa165.carpark.persistence.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author Jakub Køíž
 */

@Entity
@Table(name = "employee")
public class Employee extends User{
    
    public Employee(){
        super();
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Reservation> reservations = new ArrayList<Reservation>();
    
    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(this.reservations);
    }
    
    public void removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
    }
}
