package carDealer.entities.sales;

import carDealer.entities.cars.CarViewDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SaleViewDTO implements Serializable {

    @SerializedName("Discount")
    private Double discount;

    @SerializedName("Car")
    private CarViewDTO car;

    public SaleViewDTO() {}

    public SaleViewDTO(Double discount, CarViewDTO car) {
            this.discount = discount;
            this.car = car;
    }

    public Double getDiscount() {
            return discount;
    }

    public void setDiscount(Double discount) {
            this.discount = discount;
    }

    public CarViewDTO getCar() {
            return car;
    }

    public void setCar(CarViewDTO car) {
            this.car = car;
    }
}
