package springdatamodelmapperex.services.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdatamodelmapperex.entites.exceptions.UserNotLoggedInException;
import springdatamodelmapperex.entites.users.LoginDTO;
import springdatamodelmapperex.entites.users.RegisterDTO;
import springdatamodelmapperex.entites.users.User;
import springdatamodelmapperex.repositories.UserRepository;
import springdatamodelmapperex.services.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private User currentUser;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.currentUser = null;
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterDTO registerData) {
        ensureNoLoggedUser();
        ModelMapper mapper = new ModelMapper();
        User toRegister = mapper.map(registerData, User.class);

        long userCount = this.userRepository.count();
        if(userCount == 0){
            toRegister.setAdmin(true);
        }

        return this.userRepository.save(toRegister);
    }

    @Override
    public Optional<User> login(LoginDTO loginData) {
        ensureNoLoggedUser();
        Optional<User> user = this.userRepository.findByEmailAndPassword(
                loginData.getEmail(),
                loginData.getPassword());

        //Save the user for all next operations
        if(user.isPresent()){
            this.currentUser = user.get();
        }
        return user;
    }

    private void ensureNoLoggedUser() {
        if(this.currentUser != null){
            //throw exception / return;
        }
    }

    @Override
    public User getLoggedUser(){
        if(this.currentUser != null){
            //throw exception / return;
            throw new UserNotLoggedInException();
        }
        return this.currentUser;
    }

    @Override
    public void logout() {
//        if(currentUser != null){
//        TODO: Cannot log out. No user logged in.
        this.currentUser = null;
    }
}
