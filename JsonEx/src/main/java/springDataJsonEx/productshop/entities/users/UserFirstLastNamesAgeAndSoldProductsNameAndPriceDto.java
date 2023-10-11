package springDataJsonEx.productshop.entities.users;

import org.antlr.v4.runtime.misc.NotNull;
import springDataJsonEx.productshop.entities.products.SoldProductsForUserDto;

import java.io.Serializable;

public class UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto implements Serializable {
    private String firstName;

    @NotNull
//    @Length(min = 3)
    private String lastName;

    private Integer age;

    private SoldProductsForUserDto soldProducts;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SoldProductsForUserDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProductsForUserDto soldProducts) {
        this.soldProducts = soldProducts;
    }

    @Override
    public String toString() {
        return "UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", soldProducts=" + soldProducts +
                '}';
    }
}
