package carDealer.entities.suppliers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierViewDTO {
    @XmlElement(name = "supplier")
    private List<SupplierDTO> suppliers;

    public SupplierViewDTO() {}

    public SupplierViewDTO(List<SupplierDTO> suppliers) {
        this.suppliers = suppliers;
    }

    public List<SupplierDTO> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierDTO> suppliers) {
        this.suppliers = suppliers;
    }
}
