package productshop.entities.products;

import jakarta.persistence.*;
import productshop.entities.categories.Category;
import productshop.entities.users.User;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;

    private BigDecimal price;
    @ManyToOne
    private User buyer;
    @ManyToOne
    private User seller;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

    public Product() {
        this.categories = new HashSet<>();
    }

    public Product(String name, BigDecimal price) {
        this();
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
