package carDealer.entities.customers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerPurchasesViewDTO {
    @XmlElement(name = "customer")
    private List<CustomerViewDTO> customers;

    public CustomerPurchasesViewDTO() {}

    public CustomerPurchasesViewDTO(List<CustomerViewDTO> customers) {
        this.customers = customers;
    }

    public List<CustomerViewDTO> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    public void setCustomers(List<CustomerViewDTO> customers) {
        this.customers = customers;
    }
}
