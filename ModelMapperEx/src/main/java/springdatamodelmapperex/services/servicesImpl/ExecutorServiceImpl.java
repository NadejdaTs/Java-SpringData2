package springdatamodelmapperex.services.servicesImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdatamodelmapperex.entites.users.LoginDTO;
import springdatamodelmapperex.entites.users.RegisterDTO;
import springdatamodelmapperex.entites.users.User;
import springdatamodelmapperex.entites.users.UserBasicInfoDTO;
import springdatamodelmapperex.services.ExecutorService;
import springdatamodelmapperex.services.UserService;

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
    public String execute(String commandLine) {
        String[] commandParts = commandLine.split("\\|");
        String commandName = commandParts[0];

        String commandOutput = switch (commandName) {
            case REGISTER_USER_COMMAND -> registerUser(commandParts);
            case LOGIN_USER_COMMAND -> loginUser(commandParts);
            case LOGOUT_USER_COMMAND -> logoutUser();
            case ADD_GAME_COMMAND -> addGame();
            default -> "Unknown command";
        };
        return commandOutput;
    }

    private String loginUser(String[] commandParts) {
        LoginDTO loginData = new LoginDTO(commandParts);
        Optional<User> user = userService.login(loginData);

        if(user.isPresent()){
            return String.format("Successfully logged in %s%n", user.get().getFullName());
        }else{
            return "Wrong Credentials";
        }
    }

    private String registerUser(String[] commandParts) {
        RegisterDTO registerData = new RegisterDTO(commandParts);
        User user = userService.register(registerData);
        return String.format("%s was registered", user.getFullName());
    }

    private String addGame() {
        User loggedUser = this.userService.getLoggedUser();

        if(!loggedUser.isAdmin()){
            throw new UnsupportedOperationException("User not admin!");
        }
        return null;
    }

    private String logoutUser() {
        User loggedUser = this.userService.getLoggedUser();
        //Check if actual user

        this.userService.logout();
//        return this.mapper.map(loggedUser, UserBasicInfoDTO.class);
        return String.format("User %s successfully logged out!", loggedUser.getFullName());
    }

    /*private UserBasicInfoDTO loginUser(String data) {
        LoginDTO loginData = this.gson.fromJson(data, LoginDTO.class);

        Optional<User> user = userService.login(loginData);
        if(user.isPresent()) {
            return this.mapper.map(user.get(), UserBasicInfoDTO.class);
            //return String.format("Successfully logged in %s", user.get().getFullName());
        }
//        FIXME:
        return null;
    }*/

    /*private UserBasicInfoDTO registerUser(String data){
//        RegisterDTO registerData = this.gson.fromJson(data, RegisterDTO.class);
        RegisterDTO registerData = this.gson.fromJson(data, RegisterDTO.class);
        registerData.validate();

        User user = userService.register(registerData);
        //return String.format("%s was registered", user.getFullName());
        return this.mapper.map(user, UserBasicInfoDTO.class);
    }*/
}
