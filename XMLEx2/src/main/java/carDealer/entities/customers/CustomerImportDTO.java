package carDealer.entities.customers;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImportDTO {
    @XmlAttribute
    private String name;

    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }
}
