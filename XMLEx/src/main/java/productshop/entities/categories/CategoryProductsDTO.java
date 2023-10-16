package productshop.entities.categories;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryProductsDTO {
    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "products-count")
    private long count;

    @XmlElement(name = "average-price")
    private double price;

    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;

    public CategoryProductsDTO() {}

    public CategoryProductsDTO(String name, long count, double price, BigDecimal totalRevenue) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalRevenue = totalRevenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
