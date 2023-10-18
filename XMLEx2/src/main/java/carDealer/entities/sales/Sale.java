package carDealer.entities.sales;

import carDealer.entities.cars.Car;
import carDealer.entities.customers.Customer;
import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Car car;
    @ManyToOne
    private Customer customer;

    private Double discount;

    public Sale() {}

    public Sale(Car car, Customer customer, Double discount) {
        this.car = car;
        this.customer = customer;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
