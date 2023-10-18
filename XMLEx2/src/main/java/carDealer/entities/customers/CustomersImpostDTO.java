package carDealer.entities.customers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersImpostDTO {
    @XmlElement(name = "customer")
    private List<CustomerImportDTO> customers;

    public List<CustomerImportDTO> getCustomers() {
        return Collections.unmodifiableList(customers);
    }
}
