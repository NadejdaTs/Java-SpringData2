package carDealer.entities.cars;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsViewDTO {

    @XmlElement(name = "car")
    private List<CarDTO> cars;

    public CarsViewDTO() {}

    public CarsViewDTO(List<CarDTO> cars) {
        this.cars = cars;
    }

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = Collections.unmodifiableList(cars);
    }
}
