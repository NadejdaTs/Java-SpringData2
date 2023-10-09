package springdatamodelmapperex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdatamodelmapperex.entites.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
