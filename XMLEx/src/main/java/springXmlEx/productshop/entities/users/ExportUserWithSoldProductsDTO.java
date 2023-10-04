package springXmlEx.productshop.entities.users;

import springXmlEx.productshop.entities.products.ExportSoldProductsDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportUserWithSoldProductsDTO {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    List<ExportSoldProductsDTO> sellingItems;

    public ExportUserWithSoldProductsDTO() {}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSellingItems(List<ExportSoldProductsDTO> sellingItems) {
        this.sellingItems = sellingItems;
    }
}
