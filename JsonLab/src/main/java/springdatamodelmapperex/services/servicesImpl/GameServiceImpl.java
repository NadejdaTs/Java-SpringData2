package springdatamodelmapperex.services.servicesImpl;

import org.springframework.stereotype.Service;
import springdatamodelmapperex.entites.Game;
import springdatamodelmapperex.entites.GameViewDTO;
import springdatamodelmapperex.repositories.GameRepository;
import springdatamodelmapperex.services.GameService;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Optional<Game> findByTitle(String title) {
        return this.gameRepository.findByTitle(title);
    }

    /*@Override
    public int deleteById(int idOfGame) {
        return this.gameRepository.deleteById(idOfGame);
    }*/
}
