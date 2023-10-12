package carDealer.entities.suppliers;

import java.io.Serializable;

public class SupplierImportDTO implements Serializable {
    private String name;
    private Boolean isImporter;

    public String getName() {
        return name;
    }

    public Boolean getImporter() {
        return isImporter;
    }
}
