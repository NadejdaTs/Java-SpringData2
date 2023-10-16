package productshop.entities.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsImportDTO {
    @XmlElement(name = "product")
    private List<ProductImportDTO> products;

    public ProductsImportDTO() {}

    /*public ProductsImportDTO(List<ProductImportDTO> products) {
        this.products = new ArrayList<>();
    }*/

    public List<ProductImportDTO> getProducts() {
        return products;
    }
}
