package carDealer.entities.parts;

import carDealer.entities.cars.Car;
import carDealer.entities.suppliers.Supplier;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private BigDecimal price;
    private Long quantity;

    @ManyToOne
    private Supplier supplier;

    @ManyToMany(mappedBy = "parts", targetEntity = Car.class, fetch = FetchType.EAGER)
    private Set<Car> cars;

    public Part() {
        this.cars = new HashSet<>();
    }

    public Part(String name, BigDecimal price, Long quantity, Supplier supplier) {
        this();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<Car> getCars() {
        return Collections.unmodifiableSet(cars);
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
