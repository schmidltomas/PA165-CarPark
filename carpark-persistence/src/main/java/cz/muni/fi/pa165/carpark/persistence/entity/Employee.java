package cz.muni.fi.pa165.carpark.persistence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author Jakub Kříž
 */

@Entity
@Table(name = "employee")
public class Employee extends User implements Serializable{
    
    public Employee(){
        super();
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<Reservation> reservations = new ArrayList<>();
    
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
