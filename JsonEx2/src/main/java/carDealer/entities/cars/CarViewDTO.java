package carDealer.entities.cars;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CarViewDTO implements Serializable {
    @SerializedName("Id")
    private Long id;

    @SerializedName("Make")
    private String make;

    @SerializedName("Model")
    private String model;

    @SerializedName("TravelledDistance")
    private Long travelledDistance;

    public CarViewDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    @Override
    public String toString() {
        return "CarViewDTO{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", travelledDistance=" + travelledDistance +
                '}';
    }
}
