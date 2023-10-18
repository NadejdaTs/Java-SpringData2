package carDealer.services.api;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SeedService {
    void seedCustomers() throws IOException, JAXBException;

    void seedCars() throws IOException, JAXBException;

    void seedParts() throws IOException, JAXBException;

    void seedSuppliers() throws IOException, JAXBException;

    void seedSales() throws IOException, JAXBException;

    default void seedAll() throws JAXBException, IOException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedSales();
    }
}
