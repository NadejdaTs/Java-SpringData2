package carDealer.entities.parts;

import java.math.BigDecimal;

public class PartImportDTO {
    private String name;
    private BigDecimal price;
    private Long quantity;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getQuantity() {
        return quantity;
    }
}
