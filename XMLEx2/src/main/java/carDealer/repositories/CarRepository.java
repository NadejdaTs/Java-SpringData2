package carDealer.repositories;

import carDealer.entities.cars.Car;
import carDealer.entities.cars.CarsViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);
}
