package springdatamodelmapperex.services.servicesImpl;

import springdatamodelmapperex.services.ExecutorService;
import springdatamodelmapperex.services.UserService;
import springdatamodelmapperex.entites.users.LoginDTO;
import springdatamodelmapperex.entites.users.RegisterDTO;
import springdatamodelmapperex.entites.users.User;
import springdatamodelmapperex.entites.users.UserBasicInfoDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    private final UserService userService;

    private final Gson gson;
    private final ModelMapper mapper;

    @Autowired
    public ExecutorServiceImpl(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        this.mapper = new ModelMapper();
    }
    @Override
    public String execute(String commandName, String data){
        //for Json removed and first parameter was String sc
        //String[] commandParts = sc.split("\\|");
        //String commandName = commandParts[0];

        Object commandOutput = switch (commandName) {
            //instead of data, was commandParts
            case REGISTER_USER_COMMAND -> registerUser(data);
            case LOGIN_USER_COMMAND -> loginUser(data);
            case LOGOUT_USER_COMMAND -> logoutUser();
            case ADD_GAME_COMMAND -> addGame();
            default -> "Unknown command";
        };
        return this.gson.toJson(commandOutput);
    }

    private String addGame() {
        User loggedUser = this.userService.getLoggedUser();
        /*if(!loggedUser.isAdmin()){
            throw new UnsupportedOperationException("User not admin!");
        }*/
        return null;
    }

    private UserBasicInfoDTO logoutUser() {
        User loggedUser = this.userService.getLoggedUser();
        //Check if actual user

        this.userService.logout();
        return this.mapper.map(loggedUser, UserBasicInfoDTO.class);
    }

    private UserBasicInfoDTO loginUser(String data) {
        LoginDTO loginData = this.gson.fromJson(data, LoginDTO.class);

        Optional<User> user = userService.login(loginData);
        if(user.isPresent()) {
            return this.mapper.map(user.get(), UserBasicInfoDTO.class);
            //return String.format("Successfully logged in %s", user.get().getFullName());
        }
//        FIXME:
        return null;
    }

    private UserBasicInfoDTO registerUser(String data){
        RegisterDTO registerData = this.gson.fromJson(data, RegisterDTO.class);
        registerData.validate();

        User user = userService.register(registerData);
        //return String.format("%s was registered", user.getFullName());
        return this.mapper.map(user, UserBasicInfoDTO.class);
    }
}
