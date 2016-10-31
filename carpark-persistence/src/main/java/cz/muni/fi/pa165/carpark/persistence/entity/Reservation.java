package cz.muni.fi.pa165.carpark.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * Reservation entity
 *
 * @author Robert Taptik
 */

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column
    private Long id;

    @Column
    private Employee employee;

    @Column
    private Car car;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private long distance;

    @Column
    private String purpose;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.employee.hashCode());
        hash = 31 * hash + (this.car.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Reservation)) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.employee, other.getEmployee())) return false;
        if(!Objects.equals(this.car, other.getCar())) return false;
        return Objects.equals(this.distance, other.getDistance());
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", employee=" + employee +
                ", car=" + car +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", distance=" + distance +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}
