package springDataJsonEx.productshop.entities.products;

import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductNameAndPriceDto implements Serializable {
    @NotNull
//    @Length(min = 3)
    private String name;
    @NotNull
    private BigDecimal price;

    public ProductNameAndPriceDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductNameAndPriceDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
