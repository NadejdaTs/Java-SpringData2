package productshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productshop.entities.users.User;
import productshop.entities.users.UsersWithSoldProductsDTO;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u" +
            " WHERE " +
            "     (SELECT COUNT(p) FROM Product p WHERE p.seller = u AND p.buyer IS NOT NULL) > 0 " +
            " ORDER BY u.lastName, u.firstName" )
    List<User> findAllWithSoldProducts();

    @Query("SELECT u FROM User AS u" +
            " JOIN Product AS p ON p.seller.id = u.id" +
            " WHERE p.buyer IS NOT NULL" +
            " GROUP BY u.id" +
            " ORDER BY u.lastName, u.firstName")
    List<User> findAllWithSoldProductsOrderBySoldProductAndLastName();
}
