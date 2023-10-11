package springDataJsonEx.productshop.entities.users;

import springDataJsonEx.productshop.entities.products.SoldProductDTO;

import java.util.List;

public class UserWithSoldProductsDTO {
    private String firstName;
    private String lastName;
    private List<SoldProductDTO> sellingItems;

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

    public List<SoldProductDTO> getSellingItems() {
        return sellingItems;
    }

    public void setSellingItems(List<SoldProductDTO> sellingItems) {
        this.sellingItems = sellingItems;
    }
}
