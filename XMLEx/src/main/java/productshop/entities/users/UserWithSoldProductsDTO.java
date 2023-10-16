package productshop.entities.users;

import productshop.entities.products.SoldListOfProductsDTO;
import productshop.entities.products.SoldProductsDTO;

import javax.xml.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsDTO {
    @XmlAttribute
    private int age;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute(name = "first-name")
    private String firstName;

    private SoldListOfProductsDTO soldProducts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SoldListOfProductsDTO getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldListOfProductsDTO soldProducts) {
        this.soldProducts = soldProducts;
    }
}
