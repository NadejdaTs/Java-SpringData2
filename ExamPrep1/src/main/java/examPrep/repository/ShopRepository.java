package examPrep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import examPrep.model.Shop;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Optional<Shop> findByName(String name);
}
