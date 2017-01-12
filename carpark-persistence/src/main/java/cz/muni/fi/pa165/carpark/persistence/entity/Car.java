package cz.muni.fi.pa165.carpark.persistence.entity;

/**
 * Created by karelfajkus on 23/10/2016.
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "car")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column
    private Long id;

    @NotNull
    @Column(name = "spz", unique = true)
    private String evidenceNumber;

    @NotNull
    @Column
    private String brand;

    @NotNull
    @Column
    private String model;

    @NotNull
    @Column
    private String fuelType;

    @Min(1)
    @Column
    private double fuelConsumption;

    @Min(1)
    @Column
    private int seats;

    @NotNull
    @Column (name = "home_location")
    private String homeLocation;

    @NotNull
    @Column
    private String currentLocation;

    public String getEvidenceNumber() {
        return evidenceNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEvidenceNumber(String evidenceNumber) {
        this.evidenceNumber = evidenceNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (Double.compare(car.fuelConsumption, fuelConsumption) != 0) return false;
        if (seats != car.seats) return false;
        if (!evidenceNumber.equals(car.evidenceNumber)) return false;
        if (!brand.equals(car.brand)) return false;
        if (!model.equals(car.model)) return false;
        if (!fuelType.equals(car.fuelType)) return false;
        if (!homeLocation.equals(car.homeLocation)) return false;
        return currentLocation.equals(car.currentLocation);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = evidenceNumber.hashCode();
        result = 31 * result + brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + fuelType.hashCode();
        temp = Double.doubleToLongBits(fuelConsumption);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + seats;
        result = 31 * result + homeLocation.hashCode();
        result = 31 * result + currentLocation.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", evidenceNumber='" + evidenceNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", fuelConsumption=" + fuelConsumption +
                ", seats=" + seats +
                ", homeLocation='" + homeLocation + '\'' +
                ", currentLocation='" + currentLocation + '\'' +
                '}';
    }
}
