package carDealer.entities.sales;

import carDealer.entities.cars.CarDTO;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleViewDTO {
    @XmlElement(name = "discount")
    private Double discount;

    @XmlElement(name = "car")
    private CarDTO car;

    public SaleViewDTO() {}

    public SaleViewDTO(Double discount, CarDTO car) {
        this.discount = discount;
        this.car = car;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }
}
