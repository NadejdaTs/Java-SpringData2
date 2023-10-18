package carDealer.entities.customers;

import carDealer.entities.cars.CarShortDTO;
import carDealer.entities.sales.SaleViewDTO;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerOrderedByBirthDayDTO {
    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "birthDate")
    private String birthDate;

    @XmlElement(name = "isYoungDriver")
    private boolean isYoungDriver;

    @XmlElement(name = "sales")
    @XmlElementWrapper(name = "sale")
    private Set<SaleViewDTO> purchases;

    public CustomerOrderedByBirthDayDTO() {
        this.purchases = new HashSet<>();
    }

    public CustomerOrderedByBirthDayDTO(Long id, String name, String birthDate, boolean isYoungDriver, Set<SaleViewDTO> purchases) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
        this.purchases = purchases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleViewDTO> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<SaleViewDTO> purchases) {
        this.purchases = purchases;
    }
}
