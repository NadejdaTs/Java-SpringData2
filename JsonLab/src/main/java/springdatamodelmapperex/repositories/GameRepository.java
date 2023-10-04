package springdatamodelmapperex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdatamodelmapperex.entites.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
}
