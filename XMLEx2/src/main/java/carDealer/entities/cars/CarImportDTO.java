package carDealer.entities.cars;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportDTO {
    @XmlElement
    private String make;

    @XmlElement
    private String model;

    @XmlElement(name = "travelled-distance")
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
