package exam.model.laptops;

import exam.model.shops.Shop;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "laptops")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mac_address", unique = true, nullable = false)
    private String macAddress;

    @Column(name = "cpu_speed", nullable = false)
    private float cpuSpeed;

    private int ram;
    private long storage;
    @Column(columnDefinition = "TEXT")
    private String description;
    private BigDecimal price;
    @Column(nullable = false)
    private WarrantyType warrantyType;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id", referencedColumnName ="id")
    private Shop shop;
    public Laptop() {}

    public Laptop(String macAddress, float cpuSpeed, int ram, long storage, String description, BigDecimal price, WarrantyType warrantyType) {
        this.macAddress = macAddress;
        this.cpuSpeed = cpuSpeed;
        this.ram = ram;
        this.storage = storage;
        this.description = description;
        this.price = price;
        this.warrantyType = warrantyType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public float getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(float cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public long getStorage() {
        return storage;
    }

    public void setStorage(long storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Laptop - ").append(macAddress).append(System.lineSeparator());
        sb.append("*Cpu speed - ").append(cpuSpeed).append(System.lineSeparator());
        sb.append("**Ram - ").append(ram).append(System.lineSeparator());
        sb.append("***Storage - ").append(storage).append(System.lineSeparator());
        sb.append("****Price - ").append(price).append(System.lineSeparator());
        sb.append("#Shop name - ").append(shop.getName()).append(System.lineSeparator());
        sb.append("##Town - ").append(shop.getTown().getName()).append(System.lineSeparator());

        return sb.toString();
    }
}
