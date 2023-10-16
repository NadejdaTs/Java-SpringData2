package productshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productshop.entities.categories.Category;
import productshop.entities.categories.CategoryProductsDTO;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


   /* @Query("SELECT new springXmlEx.productshop.productshop.entities.categories.ExportCategoriesInfoDTO(" +
            " c.name, c.products.size, AVG(p.price), SUM(p.price)) " +
            " FROM Category c" +
            " JOIN c.products AS p" +
            " GROUP BY c.id" +
            " ORDER BY c.products.size, DESC")*/
//    List<ExportCategoriesInfoDTO> findAllOrderByProductsCount();
}
