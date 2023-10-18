package carDealer.entities.suppliers;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportDTO {
    @XmlAttribute
    private String name;

    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
