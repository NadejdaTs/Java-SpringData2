package springDataJsonEx.productshop.entities.products;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public class ProductsWithoutBuyerDTO {
    /*if it is interface
    String getName();
    BigDecimal getPrice();

    @Value("#{target.seller.firstName + ' ' + target.seller.lastName}")
    String getSeller();*/


    private String name;
    private BigDecimal price;
    private String seller;

    public ProductsWithoutBuyerDTO(String name, BigDecimal price, String firstName, String lastName) {
        this.name = name;
        this.price = price;

        if(firstName == null){
            this.seller = lastName;
        }else {
            this.seller = firstName + " " + lastName;
        }
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }
}
