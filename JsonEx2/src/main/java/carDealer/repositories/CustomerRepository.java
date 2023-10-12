package carDealer.repositories;

import carDealer.entities.customers.Customer;
import carDealer.entities.customers.CustomerAllDTO;
import carDealer.entities.customers.CustomerOrderedByBirthDayDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer AS c" +
            " ORDER BY c.birthDate, c.isYoungDriver")
    List<Customer> findAllByBirthDate();

    /*@Query("SELECT new carDealer.entities.customers.CustomerAllDTO(" +
            " c.id, c.name, c.birthDate, c.isYoungDriver, c.purchases) FROM Customer AS c" +
            " ORDER BY c.birthDate, c.isYoungDriver")
    List<CustomerAllDTO> findAllByBirthDate();*/

}
