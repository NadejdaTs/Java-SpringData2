package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("SELECT o FROM Offer AS o" +
            " JOIN o.apartment AS a ON o.apartment.id = a.id" +
            " WHERE a.apartmentType = :type" +
            " ORDER BY a.area DESC, o.price ASC")
    List<Offer> findAllByApartmentTypeOrderByAreaAndPrice(@Param("type") ApartmentType apartmentType);
}
