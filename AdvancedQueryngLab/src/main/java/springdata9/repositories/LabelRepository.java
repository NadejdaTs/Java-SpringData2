package springdata9.repositories;

import springdata9.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long>{
    //Optional<Label> findById(long labelId);
}
