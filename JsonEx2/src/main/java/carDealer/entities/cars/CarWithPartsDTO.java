package carDealer.entities.cars;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CarWithPartsDTO implements Serializable {
    @SerializedName("Make")
    private String make;

    @SerializedName("Model")
    private String model;

    @SerializedName("TravelledDistance")
    private Long travelledDistance;

    public CarWithPartsDTO(String make, String model, Long travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
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
