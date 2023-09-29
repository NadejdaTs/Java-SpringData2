package springdata9.repositories;

import springdata9.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameStartingWith(String startWith);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> listOfNames);

    @Query("DELETE FROM Ingredient i" +
            " WHERE i.name = :name")
    @Modifying
    int deleteByName(String name);

    @Query("UPDATE Ingredient i SET i.price = i.price + i.price * :multiplier")
    @Modifying
    void increasePriceByPercent(@Param("multiplier") BigDecimal percent);

    @Query("UPDATE Ingredient i" +
            " SET i.price = i.price + i.price * :percent" +
            " WHERE i.name = :name")
    @Modifying
    int updatePriceByGivenName(String name, BigDecimal percent);

    @Query("UPDATE Ingredient i" +
            " SET i.price = i.price + i.price * :percent" +
            " WHERE i.name IN (:listOfNames)")
    @Modifying
    int updatePriceByListOfNames(List<String> listOfNames, BigDecimal percent);
}
