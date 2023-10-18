package carDealer.entities.customers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerAllViewDTO {
    @XmlElement(name = "customer")
    private List<CustomerAllDTO> customers;

    public CustomerAllViewDTO() {}

    public CustomerAllViewDTO(List<CustomerAllDTO> customers) {
        this.customers = customers;
    }

    public List<CustomerAllDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerAllDTO> customers) {
        this.customers = customers;
    }
}
