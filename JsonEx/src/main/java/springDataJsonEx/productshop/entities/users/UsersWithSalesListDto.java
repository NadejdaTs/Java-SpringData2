package springDataJsonEx.productshop.entities.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsersWithSalesListDto implements Serializable {
    private Integer usersCount;

    private List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users;

    public UsersWithSalesListDto() {
        users = new ArrayList<>();
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersWithSalesListDto{" +
                "usersCount=" + usersCount +
                ", users=" + users +
                '}';
    }
}
