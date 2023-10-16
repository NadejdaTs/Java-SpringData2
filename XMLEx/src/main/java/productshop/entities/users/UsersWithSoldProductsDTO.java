package productshop.entities.users;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithSoldProductsDTO {
    @XmlAttribute
    private int count;

    @XmlElement(name = "user")
    private List<UserWithSoldProductsDTO> users;

    public UsersWithSoldProductsDTO() {}

    public UsersWithSoldProductsDTO(List<UserWithSoldProductsDTO> users) {
        this.users = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserWithSoldProductsDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithSoldProductsDTO> users) {
        this.users = users;
    }
}
