package carDealer.repositories;

import carDealer.entities.cars.Car;
import carDealer.entities.cars.CarDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<CarDTO> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

    /*@Query("SELECT new carDealer.entities.cars.CarWithPartsDTO(" +
            "c.make, c.model, c.travelledDistance)" +
            " FROM Car c")
    List<CarWithPartsDTO> findCarsAndPartsInfo();*/

}
