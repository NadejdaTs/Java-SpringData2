package carDealer.services.api;

import carDealer.entities.suppliers.SupplierViewDTO;

import java.util.List;

public interface SupplierService {
    SupplierViewDTO findAllIsImporter(boolean IsImporter);
}
