package productshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productshop.entities.categories.CategoryProductsDTO;
import productshop.entities.products.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(BigDecimal rangeFrom, BigDecimal rangeTo);

    @Query("SELECT new productshop.entities.categories.CategoryProductsDTO(" +
            "c.name, COUNT(p), AVG(p.price), SUM(p.price))" +
            " FROM Product p" +
            " JOIN p.categories c" +
            " GROUP BY c")
    List<CategoryProductsDTO> findAllOrderByNumberOfProduct();
}
