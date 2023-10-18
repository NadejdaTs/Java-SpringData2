package carDealer.entities.sales;

import javax.xml.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleFullViewDTO {
    @XmlElement(name = "sale")
    private List<SaleDTO> sales;

    public SaleFullViewDTO() {}

    public SaleFullViewDTO(List<SaleDTO> sales) {
        this.sales = sales;
    }

    public List<SaleDTO> getSales() {
        return Collections.unmodifiableList(sales);
    }

    public void setSales(List<SaleDTO> sales) {
        this.sales = sales;
    }
}
