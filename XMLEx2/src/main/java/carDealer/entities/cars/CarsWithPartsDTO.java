package carDealer.entities.cars;

import carDealer.entities.parts.PartsDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsWithPartsDTO {
    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    @XmlAttribute
    private String model;

    @XmlAttribute
    private String make;

    @XmlElementWrapper(name = "parts")
    @XmlElement(name = "part")
    private List<PartsDTO> parts;

    public CarsWithPartsDTO() {}

    public CarsWithPartsDTO(String make, String model, Long travelledDistance, List<PartsDTO> parts) {
        this.travelledDistance = travelledDistance;
        this.model = model;
        this.make = make;
        this.parts = parts;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public List<PartsDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartsDTO> parts) {
        this.parts = parts;
    }
}
