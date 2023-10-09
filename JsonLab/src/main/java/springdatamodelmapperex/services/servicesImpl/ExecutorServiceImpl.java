package springdatamodelmapperex.services.servicesImpl;

import springdatamodelmapperex.entites.Cart;
import springdatamodelmapperex.entites.Game;
import springdatamodelmapperex.LocalDateTypeAdapter;
import springdatamodelmapperex.entites.GameViewDTO;
import springdatamodelmapperex.entites.Order;
import springdatamodelmapperex.repositories.CartRepository;
import springdatamodelmapperex.repositories.GameRepository;
import springdatamodelmapperex.repositories.OrderRepository;
import springdatamodelmapperex.repositories.UserRepository;
import springdatamodelmapperex.services.ExecutorService;
import springdatamodelmapperex.services.GameService;
import springdatamodelmapperex.services.OrderService;
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
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    private final UserService userService;
    private final GameService gameService;
    private final OrderService orderService;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final OrderRepository orderRepository;
    private CartRepository cartRepository;

    private final Gson gson;
    private final ModelMapper mapper;
//    private Set<Game> gameInCart;


    @Autowired
    public ExecutorServiceImpl(UserService userService, Gson gson, GameService gameService, OrderService orderService, UserRepository userRepository, GameRepository gameRepository, OrderRepository orderRepository, CartRepository cartRepository) {
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderService;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
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
            case ALL_GAMES_COMMAND -> allGamesView();
            case DETAIL_GAME_VIEW -> detailGameView(data);
            case PURCHASE_GAME_COMMAND -> purchaseGame(data);
            case OWNED_GAMES_COMMAND -> ownedGames();
            case ADD_ITEM_COMMAND -> addItemToShoppingCart(data);
            case REMOVE_ITEM_COMMAND -> removeItemFromShoppingCart(data);
            case BUY_ITEM_COMMAND -> buyItemFromShoppingCart();
            default -> "Unknown command";
        };
        //two ways to display the result: with String and with Json
        if(commandName.equals(DETAIL_GAME_VIEW) || commandName.equals(ALL_GAMES_COMMAND) || commandName.equals(OWNED_GAMES_COMMAND)) {
            return commandOutput.toString();
        }else{
            return this.gson.toJson(commandOutput);
        }
    }

    private Object buyItemFromShoppingCart() {
        return null;
    }

    private String removeItemFromShoppingCart(String title) {
        User loggedUser = this.userService.getLoggedUser();
        Optional<Game> game = this.gameService.findByTitle(title);
        if(!game.isPresent()) {
            throw new IllegalArgumentException("Not existing game!");
        }
        Cart cart = loggedUser.getCart();
        Set<Game> games = cart.getGames();
        boolean isExistInCart = games.stream()
                .noneMatch(g -> g.getTitle().equals(title));
        if(isExistInCart){
            throw new IllegalArgumentException("Can not delete!");
        }
        games.remove(game.get());
        this.cartRepository.save(cart);
        loggedUser.setCart(cart);
        this.userRepository.save(loggedUser);
        return String.format("%s removed from cart!", title);
    }

    private String addItemToShoppingCart(String title) {
        User loggedUser = this.userService.getLoggedUser();
        Optional<Game> game = this.gameService.findByTitle(title);
        if(!game.isPresent()) {
            throw new IllegalArgumentException("Not existing game!");
        }
        Cart cart = loggedUser.getCart();
        if(cart != null){
            Set<Game> games = cart.getGames();
            boolean isExistInCart = games.stream()
                    .noneMatch(g -> !g.getTitle().equals(title));
            if(isExistInCart){
                throw new IllegalArgumentException("Can not order again!");
            }
            games.add(game.get());
            cart.setGames(games);
        }else{
            cart = new Cart(loggedUser);
            Set<Game> newGames = new HashSet<>();
            newGames.add(game.get());
            cart.setGames(newGames);
        }
        this.cartRepository.save(cart);
        loggedUser.setCart(cart);
        this.userRepository.save(loggedUser);
        return String.format("%s added to cart!", title);
    }

    private String purchaseGame(String title) {
        User loggedUser = this.userService.getLoggedUser();
        Optional<Game> game = this.gameService.findByTitle(title);
        if(game.isPresent()) {
            Set<Game> games = loggedUser.getGames();
            games.add(game.get());
            this.userRepository.save(loggedUser);
            return String.format("Successfully purchased game: ", title);
        }
        return "Not existing game!";
    }

    private String ownedGames() {
        User loggedUser = this.userService.getLoggedUser();
        StringBuilder sb = new StringBuilder();
        loggedUser.getGames().stream().
                forEach(g -> sb.append(g.getTitle()).append(System.lineSeparator()));
        return sb.toString();
    }

    private String detailGameView(String title) {
        Optional<Game> game = this.gameService.findByTitle(title);
        if(!game.isPresent()) {
            throw new IllegalArgumentException("Not existing game!");
        }
        GameViewDTO gameDto = mapper.map(game.get(), GameViewDTO.class);

        return gameDto.toString();
    }

    private String allGamesView() {
        StringBuilder sb = new StringBuilder();
        for (Game g : this.gameRepository.findAll()) {
            sb.append(g.getTitle()).append(" ").append(g.getPrice());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    private String addGame(String data) {
        User loggedUser = this.userService.getLoggedUser();
        if(!loggedUser.isAdmin()){
            throw new UnsupportedOperationException("User not admin!");
        }

        Game game = this.gson.fromJson(data, Game.class);
        game.validateGame();

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
//        this.gameRepository.delete(game);
//        this.gameService.deleteById(idOfGame);
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
