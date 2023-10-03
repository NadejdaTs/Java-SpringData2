package springDataJsonEx.productshop.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLCategoryStatsDTO implements Serializable {

    @XmlElement(name = "name")
    private String category;
    //@XmlElement(name = "productCount")
    private long productCount;
    //@XmlElement(name = "averagePrice")
    private double averagePrice;
    //@XmlElement(name = "totalRevenue")
    private BigDecimal totalRevenue;

    public XMLCategoryStatsDTO() {}

    public XMLCategoryStatsDTO(CategoryStatsDTO other) {
        this.category = other.getCategory();
        this.productCount = other.getProductCount();
        this.averagePrice = other.getAveragePrice();
        this.totalRevenue = other.getTotalRevenue();
    }

    @Override
    public String toString() {
        return "XMLCategoryStatsDTO{" +
                "category='" + category + '\'' +
                ", productCount=" + productCount +
                ", averagePrice=" + averagePrice +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}
