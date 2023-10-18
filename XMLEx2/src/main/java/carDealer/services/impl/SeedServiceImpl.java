package carDealer.services.impl;

import carDealer.entities.cars.Car;
import carDealer.entities.cars.CarsImportDTO;
import carDealer.entities.customers.Customer;
import carDealer.entities.customers.CustomerImportDTO;
import carDealer.entities.customers.CustomersImpostDTO;
import carDealer.entities.parts.Part;
import carDealer.entities.parts.PartsImportDTO;
import carDealer.entities.sales.Sale;
import carDealer.entities.suppliers.SuppliersImportDTO;
import carDealer.repositories.*;
import carDealer.services.api.SeedService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import carDealer.entities.suppliers.Supplier;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final ModelMapper mapper;
    private final Path SUPPLIER_FILE_PATH = Path.of("src", "main", "resources", "files", "CarDealer", "suppliers.xml");
    private final Path CARS_FILE_PATH = Path.of("src", "main", "resources", "files", "CarDealer", "cars.xml");
    private final Path CUSTOMERS_FILE_PATH = Path.of("src", "main", "resources", "files", "CarDealer", "customers.xml");
    private final Path PARTS_FILE_PATH = Path.of("src", "main", "resources", "files", "CarDealer", "parts.xml");

    @Autowired
    public SeedServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, CarRepository carRepository, CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void seedCustomers() throws IOException, JAXBException {
        BufferedReader reader = Files.newBufferedReader(CUSTOMERS_FILE_PATH);

        JAXBContext context = JAXBContext.newInstance(CustomersImpostDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        CustomersImpostDTO importDTOs = (CustomersImpostDTO) unmarshaller.unmarshal(reader);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        Converter<String, LocalDateTime> toLocalDateTime = ctx -> ctx.getSource() != null ? LocalDateTime.parse(ctx.getSource(), formatter) : null;
        TypeMap<CustomerImportDTO, Customer> typeMap = this.mapper.createTypeMap(CustomerImportDTO.class, Customer.class);

        Set<Customer> customers = importDTOs.getCustomers().stream()
                .map(dto -> typeMap.addMappings(m -> m.using(toLocalDateTime).map(src -> src.getBirthDate(), Customer::setBirthDate)).map(dto))
                .collect(Collectors.toSet());

        this.customerRepository.saveAll(customers);
    }

    @Override
    public void seedCars() throws IOException, JAXBException {
        BufferedReader reader = Files.newBufferedReader(CARS_FILE_PATH);

        JAXBContext context = JAXBContext.newInstance(CarsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        CarsImportDTO importDTOs = (CarsImportDTO) unmarshaller.unmarshal(reader);

        List<Car> cars = importDTOs.getCars().stream()
                .map(dto -> this.mapper.map(dto, Car.class))
                .map(this::getRandomParts)
                .collect(Collectors.toList());

        this.carRepository.saveAll(cars);
    }

    @Override
    public void seedParts() throws IOException, JAXBException {
        BufferedReader reader = Files.newBufferedReader(PARTS_FILE_PATH);

        JAXBContext context = JAXBContext.newInstance(PartsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PartsImportDTO importDTOs = (PartsImportDTO) unmarshaller.unmarshal(reader);

        List<Part> parts = importDTOs.getParts().stream()
                .map(dto -> this.mapper.map(dto, Part.class))
                .map(this::getRandomSupplier)
                .collect(Collectors.toList());

        this.partRepository.saveAll(parts);
    }

    @Override
    public void seedSuppliers() throws IOException, JAXBException {
        BufferedReader reader = Files.newBufferedReader(SUPPLIER_FILE_PATH);

        JAXBContext context = JAXBContext.newInstance(SuppliersImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SuppliersImportDTO importDTOs = (SuppliersImportDTO) unmarshaller.unmarshal(reader);

        List<Supplier> suppliers = importDTOs.getSuppliers().stream()
                .map(dto -> this.mapper.map(dto, Supplier.class))
                .collect(Collectors.toList());

        this.supplierRepository.saveAll(suppliers);
    }

    @Override
    public void seedSales() {
        List<Sale> sales = new ArrayList<>();
        int cnt = (int)this.carRepository.count();

        for (int i = 1; i <= cnt; i++) {
            Sale sale = new Sale(this.carRepository.findById((long)i).get(), getRandomCustomer(), getRandomDiscount());
            Sale currSale = this.mapper.map(sale, Sale.class);
            sales.add(currSale);
        }

        this.saleRepository.saveAll(sales);
    }

    private Part getRandomSupplier(Part part) {
        long count = this.supplierRepository.count();

        Random random = new Random();
        int supplierId = random.nextInt((int) count) + 1;
        Optional<Supplier> supplier = this.supplierRepository.findById((long) supplierId);
        part.setSupplier(supplier.get());
        return part;
    }

    private Car getRandomParts(Car car) {
        long countDBParts = this.partRepository.count();

        Random random = new Random();
        Set<Part> partSet = new HashSet<>();
        int count = random.nextInt(3, 5);
        for (int i = 0; i < 2; i++) {
            int partId = random.nextInt((int) countDBParts) + 1;

            Part currPart = null;
            if(!partSet.isEmpty()){
                currPart = partSet.stream()
                        .filter(p -> p.getId() == partId)
                        .findFirst()
                        .orElse(null);
            }

            if(!partSet.contains(currPart)) {
                Optional<Part> part = this.partRepository.findById((long) partId);
                partSet.add(part.get());
                this.partRepository.save(part.get());
            }
        }

        car.setParts(partSet);
        return car;
    }

    private Customer getRandomCustomer() {
        long count = this.customerRepository.count();

        Random random = new Random();
        int customerId = random.nextInt((int) count) + 1;
        Optional<Customer> customer = this.customerRepository.findById((long) customerId);

        return customer.get();
    }

    private Double getRandomDiscount() {
        Double[] listOfDiscount = {0d, 0.05d, 0.1d, 0.15d, 0.2d, 0.3d, 0.4d, 0.5d};

        Random random = new Random();
        int discountIndex = random.nextInt(8);

        return listOfDiscount[discountIndex];
    }
}
