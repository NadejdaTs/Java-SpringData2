package springdatamodelmapperex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springdatamodelmapperex.entites.Order;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
