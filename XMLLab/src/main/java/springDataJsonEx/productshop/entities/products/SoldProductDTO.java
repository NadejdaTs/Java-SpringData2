package springDataJsonEx.productshop.entities.products;

import java.math.BigDecimal;

public class SoldProductDTO {
    private String name;
    private BigDecimal price;
    private String BuyerFirstName;
    private String buyerLastName;

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

    public String getBuyerFirstName() {
        return BuyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        BuyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }
}
