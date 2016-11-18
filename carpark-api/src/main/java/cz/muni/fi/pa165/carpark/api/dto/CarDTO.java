package cz.muni.fi.pa165.carpark.api.dto;

/**
 * Created by karelfajkus on 06/11/2016.
 */
public class CarDTO {

    private Long id;

    private String evidenceNumber;

    private String brand;

    private String fuelType;

    private double fuelConsumption;

    private int seats;

    private String homeLocation;

    private String currentLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvidenceNumber() {
        return evidenceNumber;
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
        if (!(o instanceof CarDTO)) return false;

        CarDTO carDTO = (CarDTO) o;

        if (Double.compare(carDTO.fuelConsumption, fuelConsumption) != 0) return false;
        if (seats != carDTO.seats) return false;
        if (!evidenceNumber.equals(carDTO.evidenceNumber)) return false;
        if (!brand.equals(carDTO.brand)) return false;
        if (fuelType != null ? !fuelType.equals(carDTO.fuelType) : carDTO.fuelType != null) return false;
        if (!homeLocation.equals(carDTO.homeLocation)) return false;
        return currentLocation != null ? currentLocation.equals(carDTO.currentLocation) : carDTO.currentLocation == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = evidenceNumber.hashCode();
        result = 31 * result + brand.hashCode();
        result = 31 * result + (fuelType != null ? fuelType.hashCode() : 0);
        temp = Double.doubleToLongBits(fuelConsumption);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + seats;
        result = 31 * result + homeLocation.hashCode();
        result = 31 * result + (currentLocation != null ? currentLocation.hashCode() : 0);
        return result;
    }
}
