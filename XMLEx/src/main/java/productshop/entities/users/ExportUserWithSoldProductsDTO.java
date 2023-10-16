package productshop.entities.users;

import com.sun.istack.NotNull;
//import org.hibernate.validator.constraints.Length;
import productshop.entities.products.ExportSoldProductsDTO;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportUserWithSoldProductsDTO implements Serializable {
    @NotNull
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private List<ExportSoldProductsDTO> sellingItems;

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
