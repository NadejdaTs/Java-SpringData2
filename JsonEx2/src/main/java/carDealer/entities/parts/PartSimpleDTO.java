package carDealer.entities.parts;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class PartSimpleDTO implements Serializable {

    @SerializedName("Name")
    private String name;
    @SerializedName("Price")
    private BigDecimal price;

    public PartSimpleDTO() {}

    public PartSimpleDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
