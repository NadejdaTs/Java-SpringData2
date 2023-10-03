package springDataJsonEx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springDataJsonEx.productshop.entities.categories.CategoryStatsDTO;
import springDataJsonEx.productshop.entities.products.Product;
import springDataJsonEx.productshop.entities.products.ProductsWithoutBuyerDTO;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query("SELECT new springDataJsonEx.productshop.entities.products.ProductsWithoutBuyerDTO(" +
            " p.name, p.price, p.seller.firstName, p.seller.lastName)" +
            " FROM Product p" +
            " WHERE p.price > :rangeStart AND p.price < :rangeEnd AND p.buyer IS NULL" +
            " ORDER BY p.price ASC")
    List<ProductsWithoutBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal rangeStart, BigDecimal rangeEnd);

    //@Query("SELECT c.name as category, count(p) as productCount, avg(p.price) as averagePrice, sum(p.price) as totalRevenue" +
    @Query("SELECT new springDataJsonEx.productshop.entities.categories.CategoryStatsDTO(" +
            "c.name, COUNT(p), AVG(p.price), SUM(p.price))" +
            " FROM Product p" +
            " JOIN p.categories c" +
            " GROUP BY c")
    List<CategoryStatsDTO> getCategoryStats();
}
