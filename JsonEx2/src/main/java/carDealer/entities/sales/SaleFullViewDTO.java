package carDealer.entities.sales;

import carDealer.entities.cars.CarShortDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleFullViewDTO implements Serializable {
    private CarShortDTO car;
    private String customerName;

    @SerializedName("Discount")
    private Double discount;

    private BigDecimal price;
    private BigDecimal priceWithDiscount;

    public SaleFullViewDTO() {}

    public SaleFullViewDTO(CarShortDTO car, String customerName, Double discount, BigDecimal price, BigDecimal priceWithDiscount) {
        this.car = car;
        this.customerName = customerName;
        this.discount = discount;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
    }

    public CarShortDTO getCar() {
        return car;
    }

    public void setCar(CarShortDTO car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
