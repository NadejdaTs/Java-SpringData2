package carDealer.entities.cars;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsWithPartsViewDTO {
    @XmlElement(name = "car")
    private List<CarsWithPartsDTO> cars;

    public CarsWithPartsViewDTO() {}

    public CarsWithPartsViewDTO(List<CarsWithPartsDTO> cars) {
        this.cars = cars;
    }

    public List<CarsWithPartsDTO> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void setCars(List<CarsWithPartsDTO> cars) {
        this.cars = cars;
    }
}
