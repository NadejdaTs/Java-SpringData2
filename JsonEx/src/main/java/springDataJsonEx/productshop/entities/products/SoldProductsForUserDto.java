package springDataJsonEx.productshop.entities.products;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SoldProductsForUserDto implements Serializable {
    private Integer count;
    private Set<ProductNameAndPriceDto> products;

    public SoldProductsForUserDto() {
        this.products = new HashSet<>();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Set<ProductNameAndPriceDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductNameAndPriceDto> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "SoldProductsForUserDto{" +
                "count=" + count +
                ", soldProducts=" + products +
                '}';
    }
}
