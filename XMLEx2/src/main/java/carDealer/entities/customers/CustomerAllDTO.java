package carDealer.entities.customers;

import carDealer.entities.sales.Sale;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerAllDTO implements Serializable {
    @XmlElement
    private Long id;
    @XmlElement
    private String name;
    @XmlElement
    private String birthDate;
    @XmlElement
    private boolean isYoungDriver;

    public CustomerAllDTO() {}

    public CustomerAllDTO(Long id, String name, String birthDate, boolean isYoungDriver) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
