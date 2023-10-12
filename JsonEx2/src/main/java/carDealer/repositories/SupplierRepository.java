package carDealer.repositories;

import carDealer.entities.suppliers.Supplier;
import carDealer.entities.suppliers.SupplierDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT new carDealer.entities.suppliers.SupplierDTO(s.id, s.name, count(s))" +
            " FROM Supplier AS s" +
            " JOIN Part AS p ON p.supplier.id = s.id" +
            " WHERE s.isImporter = :isImporter" +
            " GROUP BY s.id")
    List<SupplierDTO> findByIsImporter(boolean isImporter);
}
