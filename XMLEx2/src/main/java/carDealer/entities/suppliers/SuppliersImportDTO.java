package carDealer.entities.suppliers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersImportDTO {

    @XmlElement(name = "supplier")
    private List<SupplierImportDTO> suppliers;

    public List<SupplierImportDTO> getSuppliers() {
        return suppliers;
    }
}
