package exam.model.shops;

import exam.model.towns.TownNameDTO;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportShopDTO {
    @XmlElement
    private String name;

    @XmlElement
    private int income;

    @XmlElement
    private String address;

    @XmlElement(name = "employee-count")
    private int employeeCount;

    @XmlElement(name = "shop-area")
    private int shopArea;

    @XmlElement(name = "town")
    private TownNameDTO town;

    public ImportShopDTO() {}

    public boolean isValid(){
        if(this.name.length() < 4){
            return false;
        }
        if(this.income < 20000){
            return false;
        }
        if(this.address.length() < 4){
            return false;
        }
        if(this.employeeCount < 1 || this.employeeCount > 50){
            return false;
        }
        if(this.shopArea < 150){
            return false;
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public int getIncome() {
        return income;
    }

    public String getAddress() {
        return address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public int getShopArea() {
        return shopArea;
    }

    public TownNameDTO getTown() {
        return town;
    }
}
