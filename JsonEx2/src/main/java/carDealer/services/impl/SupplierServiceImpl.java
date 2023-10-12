package carDealer.services.impl;

import carDealer.entities.suppliers.Supplier;
import carDealer.entities.suppliers.SupplierDTO;
import carDealer.repositories.SupplierRepository;
import carDealer.services.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierDTO> findAllIsImporter(boolean IsImporter) {
        return this.supplierRepository.findByIsImporter(IsImporter);
    }
}
