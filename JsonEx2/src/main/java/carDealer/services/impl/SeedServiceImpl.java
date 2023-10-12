package carDealer.services.impl;

import carDealer.LocalDateTimeTypeAdapter;
import carDealer.entities.cars.Car;
import carDealer.entities.cars.CarImportDTO;
import carDealer.entities.customers.Customer;
import carDealer.entities.customers.CustomerImportDTO;
import carDealer.entities.parts.Part;
import carDealer.entities.parts.PartImportDTO;
import carDealer.entities.sales.Sale;
import carDealer.repositories.*;
import carDealer.entities.suppliers.Supplier;
import carDealer.entities.suppliers.SupplierImportDTO;
import carDealer.services.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final SupplierRepository supplierRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final PartRepository partRepository;
    private final SaleRepository saleRepository;

    private final Gson gson;
    private final ModelMapper mapper;

    private final String MAIN_PATH = "src/main/resources/files/";
    private final String SUPPLIER_FILE_PATH = "suppliers.json";
    private final String CARS_FILE_PATH = "cars.json";
    private final String CUSTOMERS_FILE_PATH = "customers.json";
    private final String PARTS_FILE_PATH = "parts.json";

    public SeedServiceImpl(SupplierRepository supplierRepository, CarRepository carRepository, CustomerRepository customerRepository, PartRepository partRepository, SaleRepository saleRepository) {
        this.supplierRepository = supplierRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.partRepository = partRepository;
        this.saleRepository = saleRepository;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();
        this.mapper = new ModelMapper();
    }

    @Override
    public void seedSuppliers() throws FileNotFoundException {
        FileReader reader = new FileReader(MAIN_PATH+SUPPLIER_FILE_PATH);

        SupplierImportDTO[] suppliersDTOs = this.gson.fromJson(reader, SupplierImportDTO[].class);

        Converter<Boolean, Integer> getBoolValues = ctx -> ctx.getSource() == true ? 1 : 0;

        List<Supplier> suppliers = Arrays.stream(suppliersDTOs)
                .map(supplierDTO -> this.mapper.map(supplierDTO, Supplier.class))
                .collect(Collectors.toList());

        this.supplierRepository.saveAll(suppliers);
    }

    @Override
    public void seedCars() throws FileNotFoundException {
        FileReader reader = new FileReader(MAIN_PATH+CARS_FILE_PATH);

        CarImportDTO[] carsDTOs = this.gson.fromJson(reader, CarImportDTO[].class);

        List<Car> cars = Arrays.stream(carsDTOs)
                .map(carDTO -> this.mapper.map(carDTO, Car.class))
                .map(this::getRandomParts)
                .collect(Collectors.toList());

        this.carRepository.saveAll(cars);
    }

    @Override
    public void seedCustomers() throws FileNotFoundException {
        FileReader reader = new FileReader(MAIN_PATH+ CUSTOMERS_FILE_PATH);

        CustomerImportDTO[] customerDTOs = this.gson.fromJson(reader, CustomerImportDTO[].class);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        Converter<String, LocalDateTime> toLocalDateTime = ctx -> ctx.getSource() != null ? LocalDateTime.parse(ctx.getSource(), formatter) : null;

        TypeMap<CustomerImportDTO, Customer> typeMap = this.mapper.createTypeMap(CustomerImportDTO.class, Customer.class);
        List<Customer> customers = Arrays.stream(customerDTOs)
                .map(custDTO -> typeMap
                        .addMappings(m -> m.using(toLocalDateTime)
                                .map(src -> src.getBirthDate(), Customer::setBirthDate)).map(custDTO))
                .collect(Collectors.toList());

        this.customerRepository.saveAll(customers);
    }

    @Override
    public void seedParts() throws FileNotFoundException {
        FileReader reader = new FileReader(MAIN_PATH+ PARTS_FILE_PATH);

        PartImportDTO[] partDTOs = this.gson.fromJson(reader, PartImportDTO[].class);

        List<Part> parts = Arrays.stream(partDTOs).map(partDTO -> this.mapper
                .map(partDTO, Part.class))
                .map(this::getRandomSupplier)
                .collect(Collectors.toList());

        this.partRepository.saveAll(parts);
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

    /*private Car getRandomCar(int carId) {
        long count = this.carRepository.count();

//        Random random = new Random();
//        int carId = random.nextInt((int) count) + 1;

        Optional<Car> car = this.carRepository.findById((long) carId);

        return car.get();
    }*/

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

    private Part getRandomSupplier(Part part) {
        long count = this.supplierRepository.count();

        Random random = new Random();
        int supplierId = random.nextInt((int) count) + 1;
        Optional<Supplier> supplier = this.supplierRepository.findById((long) supplierId);
        part.setSupplier(supplier.get());
        return part;
    }

    private Car getRandomParts(Car car) {
        long count = this.partRepository.count();

        Random random = new Random();
        Set<Part> partSet = new HashSet<>();
//        int randomParts = random.nextInt(3, 5);
//        int randomParts = 3 + random.nextInt(5 - 3 + 1);
        Random random1 = new Random();
        int randomParts = random1.nextInt(5) + 1;
        if(randomParts < 3){
            randomParts = 3;
        }
        if(randomParts > 5){
            randomParts = 5;
        }

//        Random randomForParts = new Random();
        for (int i = 0; i < randomParts; i++) {
            int partId = random.nextInt((int) count) + 1;
            boolean isSameId = partSet.stream().noneMatch(p -> p.getId() == partId);
            if(isSameId && partSet.size() < 6) {
                Optional<Part> part = this.partRepository.findById((long) partId);
                partSet.add(part.get());
            }
        }

        car.setParts(partSet);
        return car;
    }
}
