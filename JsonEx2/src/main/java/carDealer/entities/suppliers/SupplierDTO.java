package carDealer.entities.suppliers;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SupplierDTO implements Serializable {
    @SerializedName("Id")
    private Long id;
    @SerializedName("Name")
    private String name;
    private Long partsCount;

    public SupplierDTO() {}

    public SupplierDTO(Long id, String name, Long partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPartsCount() {
        return partsCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPartsCount(Long partsCount) {
        this.partsCount = partsCount;
    }
}
