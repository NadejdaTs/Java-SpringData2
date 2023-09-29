package springdatamodelmapperex.services;

import springdatamodelmapperex.entites.users.LoginDTO;
import springdatamodelmapperex.entites.users.RegisterDTO;
import springdatamodelmapperex.entites.users.User;

import java.util.Optional;

public interface UserService {
    User register(RegisterDTO registerData);
    Optional<User> login(LoginDTO loginData);
    User getLoggedUser();
    void logout();
}
