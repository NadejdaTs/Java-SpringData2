package exam.repository;

import exam.models.entity.City;
import exam.models.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {


    Optional<City> findByCityName(String cityName);
}
