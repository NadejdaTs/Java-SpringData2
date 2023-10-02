package springDataJsonEx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springDataJsonEx.productshop.entities.categories.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
