package carDealer.entities.cars;

public class CarImportDTO {
    private String make;
    private String model;
    private Long travelledDistance;


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
