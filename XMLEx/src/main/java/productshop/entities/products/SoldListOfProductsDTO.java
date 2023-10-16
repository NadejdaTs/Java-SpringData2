package productshop.entities.products;

import jakarta.persistence.SecondaryTable;

import javax.xml.bind.annotation.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldListOfProductsDTO {
    @XmlAttribute
    private int count;

    @XmlElement(name = "product")
    private Set<SoldProductsDTO> products;

    public SoldListOfProductsDTO() {
        this.products = new HashSet<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<SoldProductsDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<SoldProductsDTO> products) {
        this.products = Collections.unmodifiableSet(products);
    }
}
