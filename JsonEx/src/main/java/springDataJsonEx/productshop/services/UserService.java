package springDataJsonEx.productshop.services;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import springDataJsonEx.productshop.entities.users.User;
import springDataJsonEx.productshop.entities.users.UserWithSoldProductsDTO;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductsDTO> getUserWithSoldProducts();

    List<User> getUserWithSoldProductsOrderByCount();

}
