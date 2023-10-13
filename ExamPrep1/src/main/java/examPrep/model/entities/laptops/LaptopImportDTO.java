package examPrep.model.entities.laptops;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public class LaptopImportDTO {
    private String macAddress;
    private float cpuSpeed;
    private int ram;
    private long storage;
    private String description;
    private BigDecimal price;
    private WarrantyType warrantyType;
    private LaptopShopNameDTO shop;

    public LaptopImportDTO() {
    }

    public boolean validate() {
        if(this.macAddress.length() <= 8){
            return false;
        }
        if(this.cpuSpeed < 0){
            return false;
        }
        if(this.ram < 8 || this.ram > 128){
            return false;
        }
        if(this.price.compareTo(BigDecimal.valueOf(0)) == -1){
            return false;
        }
        if(this.storage < 128 || this.storage > 1024){
            return false;
        }
        if(this.description.length() < 10){
            return false;
        }
        if(this.warrantyType == null){
            return false;
        }

        return true;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public float getCpuSpeed() {
        return cpuSpeed;
    }

    public int getRam() {
        return ram;
    }

    public long getStorage() {
        return storage;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public LaptopShopNameDTO getShop() {
        return shop;
    }
}
