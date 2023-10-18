package carDealer.entities.suppliers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierDTO {
    @XmlAttribute(name = "parts-count")
    private Long count;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private Long id;

    public SupplierDTO() {}

    public SupplierDTO(Long id, String name, Long count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
