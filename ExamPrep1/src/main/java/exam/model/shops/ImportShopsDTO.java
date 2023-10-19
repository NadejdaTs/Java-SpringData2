package exam.model.shops;

import javax.xml.bind.annotation.*;
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
