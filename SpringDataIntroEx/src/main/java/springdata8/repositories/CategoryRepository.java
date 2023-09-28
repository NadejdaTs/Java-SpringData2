package springdata8.repositories;

import springdata8.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    //List<Category> findByName(String name);
}
