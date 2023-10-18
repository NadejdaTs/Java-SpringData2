package carDealer.entities.cars;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarDTO {
    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    @XmlAttribute
    private String model;

    @XmlAttribute
    private String make;

    @XmlAttribute
    private Long id;

    public CarDTO() {}

    public CarDTO(Long travelledDistance, String model, String make, Long id) {
        this.travelledDistance = travelledDistance;
        this.model = model;
        this.make = make;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
