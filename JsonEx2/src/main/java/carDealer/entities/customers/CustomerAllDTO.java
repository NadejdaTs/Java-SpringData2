package carDealer.entities.customers;

import carDealer.entities.sales.SaleViewDTO;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Set;

public class CustomerAllDTO {
    @SerializedName("Id")
    private Long id;

    @SerializedName("Name")
    private String name;

    @SerializedName("BirthDate")
    private LocalDateTime birthDate;

    @SerializedName("IsYoungDriver")
    private boolean isYoungDriver;

    @SerializedName("Sales")
    private Long purchases;

    public CustomerAllDTO(Long id, String name, LocalDateTime birthDate, boolean isYoungDriver, Long purchases) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
        this.purchases = purchases;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public Long getPurchases() {
        return purchases;
    }
}
