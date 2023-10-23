package exam.repository;

import exam.models.entity.DayOfWeek;
import exam.models.entity.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    @Query("SELECT f FROM Forecast AS f" +
            " JOIN f.city AS c" +
            " WHERE f.dayOfWeek = :dayOfWeek " +
            " AND c.population < 150000" +
            " ORDER BY f.maxTemperature DESC, f.id ASC")
    List<Forecast> findAllFromSundayAndCitiesPopulationOrdered(@Param("dayOfWeek") DayOfWeek sunday);

    @Query("SELECT f FROM Forecast AS f" +
            " JOIN f.city AS c" +
            " WHERE f.dayOfWeek = :dayOfWeek " +
            " AND c.id = :cityId")
    Optional<Forecast> findByDayOfWeekAndCityId(@Param("cityId") Long city, @Param("dayOfWeek") DayOfWeek day);
}
