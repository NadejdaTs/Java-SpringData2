package springdatamodelmapperex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springdatamodelmapperex.entites.Game;
import springdatamodelmapperex.entites.GameViewDTO;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    Optional<Game> findByTitle(String title);

    /*@Query("DELETE g FROM Game g")
    @Modifying
    int deleteById(int id);*/
}
