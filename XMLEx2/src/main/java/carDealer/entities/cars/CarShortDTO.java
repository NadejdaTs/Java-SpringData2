package carDealer.entities.cars;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarShortDTO {
    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    @XmlAttribute
    private String model;

    @XmlAttribute
    private String make;

    public CarShortDTO() {}

    public CarShortDTO(String make, String model, Long travelledDistance) {
        this.travelledDistance = travelledDistance;
        this.model = model;
        this.make = make;
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
}
