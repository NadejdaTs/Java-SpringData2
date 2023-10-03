package springDataJsonEx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springDataJsonEx.productshop.entities.users.User;
import springDataJsonEx.productshop.entities.users.UserWithSoldProductsDTO;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u" +
            " JOIN u.sellingItems p" +
            " WHERE p.buyer IS NOT NULL")
    List<User> findAllWithSoldProducts();

    @Query("SELECT u FROM User u" +
            " JOIN u.sellingItems p" +
            " WHERE p.buyer IS NOT NULL" +
            " ORDER BY size(u.sellingItems) desc, u.lastName asc")
    List<User> findAllWithSoldProductsOrderByCount();
}
