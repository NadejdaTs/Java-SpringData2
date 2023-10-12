package carDealer;

import carDealer.entities.cars.Car;
import carDealer.entities.cars.CarDTO;
import carDealer.entities.cars.CarViewDTO;
import carDealer.entities.cars.CarWithPartsDTO;
import carDealer.entities.customers.Customer;
import carDealer.entities.customers.CustomerAllDTO;
import carDealer.entities.customers.CustomerOrderedByBirthDayDTO;
import carDealer.entities.parts.Part;
import carDealer.entities.sales.SaleViewDTO;
import carDealer.entities.suppliers.Supplier;
import carDealer.entities.suppliers.SupplierDTO;
import carDealer.repositories.CustomerRepository;
import carDealer.services.CarService;
import carDealer.services.CustomerService;
import carDealer.services.SeedService;
import carDealer.services.SupplierService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductShopRunner implements CommandLineRunner {
    private final Gson gson;
    private final ModelMapper mapper;
    private final SeedService seedService;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final CarService carService;
    private final SupplierService supplierService;

    @Autowired
    public ProductShopRunner(SeedService seedService, CustomerService customerService, CustomerRepository customerRepository, CarService carService, SupplierService supplierService) {
        this.customerRepository = customerRepository;
        this.carService = carService;
        this.supplierService = supplierService;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();
        this.mapper = new ModelMapper();
        this.seedService = seedService;
        this.customerService = customerService;
    }


    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();
//        Query 1
//        List<CustomerOrderedByBirthDayDTO> result = getQueryOne();
//        trying
//        List<CustomerAllDTO> result = this.customerService.findAllOrderByBirthDateIsYoungDriver();
//        Query 2
//        List<CarDTO> result = this.carService.findAllByMake("Toyota");
//        Query 3
//        List<SupplierDTO> result = this.supplierService.findAllIsImporter(false);
//        Query 4
//        ???
        List<Car> result = this.carService.findCarsAndPartsInfo("Opel");
        String json = gson.toJson(result);
        System.out.println(json);
    }

    private List<CustomerOrderedByBirthDayDTO> getQueryOne() {
        List<Customer> allCustomersOrdered = this.customerService.findAllOrderByBirthDateIsYoungDriver();

        List<CustomerOrderedByBirthDayDTO> result = allCustomersOrdered
                .stream()
                .map(customer -> {
                    CustomerOrderedByBirthDayDTO customerDto = this.mapper.map(customer, CustomerOrderedByBirthDayDTO.class);
                    customerDto.setPurchases(customer
                            .getPurchases()
                            .stream()
                            .map(sale -> {
                                final SaleViewDTO saleViewDto = this.mapper.map(sale, SaleViewDTO.class);
                                saleViewDto.setCar(this.mapper.map(sale.getCar(), CarViewDTO.class));
                                return saleViewDto;
                            })
                            .collect(Collectors.toSet()));
                    return customerDto;
                })
                .collect(Collectors.toList());

        return result;
    }
}
