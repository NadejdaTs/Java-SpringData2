package examPrep.model.entities.shops;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportShopsDTO {
    @XmlElement(name = "shop")
    private List<ImportShopDTO> shops;

    public ImportShopsDTO() {}

    public List<ImportShopDTO> getShops() {
        return this.shops;
    }
}
