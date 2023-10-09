package springdatamodelmapperex.services;

import springdatamodelmapperex.entites.Game;
import springdatamodelmapperex.entites.GameViewDTO;

import java.util.Optional;

public interface GameService {
    Optional<Game> findByTitle(String title);

//    int deleteById(int idOfGame);
}
