package productshop.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryProductDTO {
    @XmlElement(name = "category")
    private List<CategoryProductsDTO> categoryProducts;

    public CategoryProductDTO() {}

    public CategoryProductDTO(List<CategoryProductsDTO> categoryProducts) {
        this.categoryProducts = categoryProducts;
    }

    public void setCategoryProducts(List<CategoryProductsDTO> categoryProducts) {
        this.categoryProducts = categoryProducts;
    }
}
