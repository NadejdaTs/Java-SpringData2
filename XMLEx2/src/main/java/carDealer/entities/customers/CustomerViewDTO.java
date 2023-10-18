package carDealer.entities.customers;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerViewDTO {
    @XmlAttribute(name = "spent-money")
    private BigDecimal spentMoney;
    @XmlAttribute(name = "bought-cars")
    private Integer boughtCars;
    @XmlAttribute(name = "full-name")
    private String name;

    public CustomerViewDTO() {}

    public CustomerViewDTO(String name, Integer boughtCars, BigDecimal spentMoney) {
        this.name = name;
        this.boughtCars = boughtCars;
        this.spentMoney = spentMoney;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }

    public Integer getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
