package carDealer.entities.cars;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CarDTO implements Serializable {
    @SerializedName("Id")
    private Long id;
    @SerializedName("Make")
    private String make;
    @SerializedName("Model")
    private String model;
    @SerializedName("TravelledDistance")
    private Long travelledDistance;

    public CarDTO(Long id, String make, String model, Long travelledDistance) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }
}
