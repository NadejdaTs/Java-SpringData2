package springdatamodelmapperex.services.servicesImpl;

import springdatamodelmapperex.entites.Game;
import springdatamodelmapperex.LocalDateTypeAdapter;
import springdatamodelmapperex.repositories.GameRepository;
import springdatamodelmapperex.repositories.UserRepository;
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

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    private final Gson gson;
    private final ModelMapper mapper;

    @Autowired
    public ExecutorServiceImpl(UserService userService, Gson gson, UserRepository userRepository, GameRepository gameRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        this.mapper = new ModelMapper();
    }
    @Override
    public String execute(String commandName, String data){
        //for Json removed and first parameter was String sc
        //String[] commandParts = sc.split("\\|");
        //String commandName = commandParts[0];

        Object commandOutput = switch (commandName) {
            case REGISTER_USER_COMMAND -> registerUser(data);
            case LOGIN_USER_COMMAND -> loginUser(data);
            case LOGOUT_USER_COMMAND -> logoutUser();
            case ADD_GAME_COMMAND -> addGame(data);
            case DELETE_GAME_COMMAND -> deleteGame(data);
            default -> "Unknown command";
        };
        return this.gson.toJson(commandOutput);
    }

    private String addGame(String data) {
        User loggedUser = this.userService.getLoggedUser();
        if(!loggedUser.isAdmin()){
            throw new UnsupportedOperationException("User not admin!");
        }

        Game game = this.gson.fromJson(data, Game.class);

        Set<Game> games = loggedUser.getGames();
        if(loggedUser.getGames().contains(game)){
            throw new IllegalArgumentException("Existing game!");
        }
        games.add(game);
        this.gameRepository.save(game);
//        this.userRepository.save(loggedUser);
        return "Game was added successfully!";
    }

    private String deleteGame(String data) {
        int idOfGame = Integer.parseInt(data);
        Game game = this.gameRepository.findAll().stream()
                .filter(g -> g.getId() == idOfGame)
                .findFirst()
                .orElse(null);
        if(game == null){
            throw new IllegalArgumentException("Invalid id of game!");
        }
        this.gameRepository.delete(game);
        return String.format("Successfully deleted game!%s", game.getTitle());
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
        }
//        FIXME:
        return null;
    }

    private UserBasicInfoDTO registerUser(String data){
        RegisterDTO registerData = this.gson.fromJson(data, RegisterDTO.class);
        registerData.validate();

        User user = userService.register(registerData);
        return this.mapper.map(user, UserBasicInfoDTO.class);
    }
}
