package carDealer;

import carDealer.entities.cars.CarShortDTO;
import carDealer.entities.cars.CarViewDTO;
import carDealer.entities.cars.CarWithPartsDTO;
import carDealer.entities.customers.Customer;
import carDealer.entities.customers.CustomerAllDTO;
import carDealer.entities.customers.CustomerOrderedByBirthDayDTO;
import carDealer.entities.customers.CustomerPurchasesViewDTO;
import carDealer.entities.parts.Part;
import carDealer.entities.parts.PartSimpleDTO;
import carDealer.entities.sales.SaleFullViewDTO;
import carDealer.entities.sales.SaleViewDTO;
import carDealer.repositories.CarRepository;
import carDealer.repositories.CustomerRepository;
import carDealer.repositories.SaleRepository;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductShopRunner implements CommandLineRunner {
    private final Gson gson;
    private final ModelMapper mapper;
    private final SeedService seedService;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CarService carService;
    private final SupplierService supplierService;

    @Autowired
    public ProductShopRunner(SeedService seedService, CustomerService customerService, CustomerRepository customerRepository, SaleRepository saleRepository, CarRepository carRepository, CarService carService, SupplierService supplierService) {
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
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
        this.seedService.seedCars();
//        Query 1
//        List<CustomerAllDTO> result = this.customerService.findAllOrderedCustomerByBirthDateAndIsYoungDriver();
//        extended version
//        List<CustomerOrderedByBirthDayDTO> result = getQueryOne();
//        Query 2
//        List<CarDTO> result = this.carService.findAllByMake("Toyota");
//        Query 3
//        List<SupplierDTO> result = this.supplierService.findAllIsImporter(false);
//        Query 4
//        List<CarWithPartsDTO> result = getCarWithPartsDTOS();
//        Query 5
//        List<CustomerPurchasesViewDTO> result = getCustomerPurchasesViewDTO();
//        Query 6
//        List<SaleFullViewDTO> result = getSaleFullViewDTOS();

        /*String json = gson.toJson(result);
        System.out.println(json);*/
    }

    private List<CarWithPartsDTO> getCarWithPartsDTOS() {
        List<CarWithPartsDTO> result = this.carRepository.findAll().stream()
                .map(car -> {
                    CarWithPartsDTO carPartsDTO = new CarWithPartsDTO();
                    carPartsDTO.setCar(this.mapper.map(car, CarShortDTO.class));
                    carPartsDTO.setParts(car
                            .getParts().stream()
                            .map(part -> this.mapper.map(part, PartSimpleDTO.class))
                            .collect(Collectors.toSet())
                    );
                    return carPartsDTO;
                })
                .collect(Collectors.toList());
        return result;
    }

    private List<CustomerPurchasesViewDTO> getCustomerPurchasesViewDTO() {
        List<CustomerPurchasesViewDTO> result = this.customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerPurchasesViewDTO customerDTO = new CustomerPurchasesViewDTO();
                    customerDTO.setName(customer.getName());
                    customerDTO.setBoughtCars(customer.getPurchases().size());
                    BigDecimal spentMoney = customer.getPurchases().stream()
                            .map(sale -> sale.getCar().getParts().stream()
                                    .map(Part::getPrice)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    customerDTO.setSpentMoney(spentMoney);
                    return customerDTO;
                })
                .sorted((c1,c2) -> {
                    int cmp = c2.getSpentMoney().compareTo(c1.getSpentMoney());
                    if(cmp == 0){
                        cmp = c2.getBoughtCars().compareTo(c1.getBoughtCars());
                    }
                    return cmp;
                })
                .collect(Collectors.toList());
        return result;
    }

    private List<SaleFullViewDTO> getSaleFullViewDTOS() {
        List<SaleFullViewDTO> result = this.saleRepository.findAll().stream()
                .map(sale -> {
                    SaleFullViewDTO saleDTO = new SaleFullViewDTO();
                    saleDTO.setCar(this.mapper.map(sale.getCar(), CarShortDTO.class));
                    saleDTO.setCustomerName(sale.getCustomer().getName());
                    saleDTO.setDiscount(sale.getDiscount());

                    saleDTO.setPrice(sale.getCar().getParts().stream()
                            .map(p -> p.getPrice())
                            .reduce(BigDecimal.ZERO, BigDecimal::add));

                    saleDTO.setPriceWithDiscount(saleDTO.getPrice()
                            .multiply(BigDecimal.valueOf(1.0d - saleDTO.getDiscount())));
                    return saleDTO;
                })
                .collect(Collectors.toList());
        return result;
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
