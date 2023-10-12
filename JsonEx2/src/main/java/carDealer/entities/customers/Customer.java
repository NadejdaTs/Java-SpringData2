package carDealer.entities.customers;

import carDealer.entities.sales.Sale;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime birthDate;

    @Column(name = "is_young_driver")
    private Boolean isYoungDriver;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Sale> purchases;

    public Customer() {
        this.purchases = new HashSet<>();
    }

    public Customer(String name, LocalDateTime birthDate, Boolean isYoungDriver) {
        this();
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
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

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<Sale> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Sale> purchases) {
        this.purchases = Collections.unmodifiableSet(purchases);
    }
}
