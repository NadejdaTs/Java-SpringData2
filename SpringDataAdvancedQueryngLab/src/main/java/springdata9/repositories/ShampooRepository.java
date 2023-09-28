package springdata9.repositories;

import springdata9.entities.Shampoo;
import springdata9.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findByBrand(String brand);
    List<Shampoo> findAllByBrandAndSize(String brand, Size size);
    List<Shampoo> findBySizeOrderById(Size size);

    @Query("SELECT s FROM Shampoo AS s"+
            " JOIN s.ingredients AS i"+
            " WHERE i.name IN :ingredientNames")
    List<Shampoo> findByIngredientsNames(Set<String> ingredientNames);

    List<Shampoo> findBySize(Size size);

    //List<Shampoo> findBySizeOrLabelOrderByPriceAsc(Optional<Label> label);

    List<Shampoo> findByPriceGreaterThanOrderByPriceAsc(BigDecimal price);

    int countByPriceLessThan(BigDecimal price);

    /*@Query("SELECT s FROM Shampoo AS s" +
            " JOIN Ingredient AS i " +
            " WHERE i.shampoos.size < :count")
    List<Shampoo> findByIngredientCountLessThan(long count);*/
}
