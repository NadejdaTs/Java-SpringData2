package carDealer.services.impl;

import carDealer.entities.suppliers.SupplierDTO;
import carDealer.entities.suppliers.SupplierViewDTO;
import carDealer.repositories.SupplierRepository;
import carDealer.services.api.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public SupplierViewDTO findAllIsImporter(boolean IsImporter) {
        List<SupplierDTO> supplierDTOs = this.supplierRepository.findByIsImporter(IsImporter);

        return new SupplierViewDTO(supplierDTOs);
    }
}
