package exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import exam.model.shops.Shop;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Optional<Shop> findByName(String name);
}
