package carDealer.entities.customers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerOrderedByBirthDayViewDTO {
    @XmlElement(name = "customer")
    private List<CustomerOrderedByBirthDayDTO> customers;

    public CustomerOrderedByBirthDayViewDTO() {}

    public CustomerOrderedByBirthDayViewDTO(List<CustomerOrderedByBirthDayDTO> customers) {
        this.customers = customers;
    }

    public List<CustomerOrderedByBirthDayDTO> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    public void setCustomers(List<CustomerOrderedByBirthDayDTO> customers) {
        this.customers = customers;
    }
}
