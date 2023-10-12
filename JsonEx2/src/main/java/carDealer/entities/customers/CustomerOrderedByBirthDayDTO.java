package carDealer.entities.customers;

import carDealer.entities.sales.Sale;
import carDealer.entities.sales.SaleViewDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerOrderedByBirthDayDTO implements Serializable {
    @SerializedName("Id")
    private Long id;

    @SerializedName("Name")
    private String name;

    @SerializedName("BirthDate")
    private LocalDateTime birthDate;

    @SerializedName("IsYoungDriver")
    private boolean isYoungDriver;

    @SerializedName("Sales")
    private Set<SaleViewDTO> purchases;

    public CustomerOrderedByBirthDayDTO() {
        this.purchases = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleViewDTO> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<SaleViewDTO> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "CustomerOrderedByBirthDayDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", isYoungDriver=" + isYoungDriver +
                ", purchases=" + purchases +
                '}';
    }
}
