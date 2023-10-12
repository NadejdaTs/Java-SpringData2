package carDealer.services;

import carDealer.entities.suppliers.Supplier;
import carDealer.entities.suppliers.SupplierDTO;
import carDealer.entities.suppliers.SupplierImportDTO;

import java.util.List;

public interface SupplierService {
    List<SupplierDTO> findAllIsImporter(boolean IsImporter);
}
