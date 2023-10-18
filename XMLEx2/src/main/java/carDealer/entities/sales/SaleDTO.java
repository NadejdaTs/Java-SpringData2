package carDealer.entities.sales;

import carDealer.entities.cars.CarShortDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleDTO {
    @XmlElement(name = "car")
    private CarShortDTO car;

    @XmlElement(name = "customer-name")
    private String name;

    @XmlElement
    private Double discount;

    @XmlElement
    private BigDecimal price;

    @XmlElement(name = "price-with-discount")
    private BigDecimal priceWithDiscount;

    public SaleDTO() {}

    public SaleDTO(CarShortDTO car, String name, Double discount, BigDecimal price, BigDecimal priceWithDiscount) {
        this.car = car;
        this.name = name;
        this.discount = discount;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
    }

    public CarShortDTO getCar() {
        return car;
    }

    public void setCar(CarShortDTO car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
