package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Apartment;

import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query("SELECT a FROM Apartment AS a" +
            " JOIN Town AS t ON a.town.id = t.id" +
            " WHERE a.area = :areaOfApartment AND t.name = :townName")
    Optional<Apartment> findByTownNameAndArea(@Param("townName") String name, @Param("areaOfApartment") double area);
}
