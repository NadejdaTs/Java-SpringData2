package springDataJsonEx.productshop.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories-stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLCategoryStatList {
    @XmlElementWrapper(name = "categories")
    private List<XMLCategoryStatsDTO> stats;

    public XMLCategoryStatList(){}

    public XMLCategoryStatList(List<XMLCategoryStatsDTO> stats) {
        this.stats = stats;
    }
}
