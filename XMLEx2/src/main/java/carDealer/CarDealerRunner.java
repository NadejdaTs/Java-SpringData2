package carDealer;

import carDealer.entities.cars.CarDTO;
import carDealer.entities.cars.CarShortDTO;
import carDealer.entities.cars.CarsWithPartsDTO;
import carDealer.entities.cars.CarsWithPartsViewDTO;
import carDealer.entities.customers.*;
import carDealer.entities.parts.Part;
import carDealer.entities.parts.PartsDTO;
import carDealer.entities.sales.SaleDTO;
import carDealer.entities.sales.SaleFullViewDTO;
import carDealer.entities.sales.SaleViewDTO;
import carDealer.repositories.CarRepository;
import carDealer.repositories.CustomerRepository;
import carDealer.repositories.SaleRepository;
import carDealer.services.api.CarService;
import carDealer.services.api.CustomerService;
import carDealer.services.api.SeedService;
import carDealer.services.api.SupplierService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarDealerRunner implements CommandLineRunner {
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
    public CarDealerRunner(SeedService seedService, CustomerService customerService, CarService carService,
                           CustomerRepository customerRepository, SaleRepository saleRepository,
                           CarRepository carRepository, SupplierService supplierService) {
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
//        Query 1
//        CustomerAllViewDTO result = this.customerService.findAllOrderedCustomerByBirthDateAndIsYoungDriver();
//        JAXBContext context = JAXBContext.newInstance(CustomerAllViewDTO.class);
//        extended version
//        CustomerOrderedByBirthDayViewDTO result = getQueryOne();
//        JAXBContext context = JAXBContext.newInstance(CustomerOrderedByBirthDayViewDTO.class);
//        Query 2
//        CarsViewDTO result = this.carService.findAllByMake("Toyota");
//        JAXBContext context = JAXBContext.newInstance(CarsViewDTO.class);
//        Query 3
//        SupplierViewDTO result = this.supplierService.findAllIsImporter(false);
//        JAXBContext context = JAXBContext.newInstance(SupplierViewDTO.class);
//        Query 4
//        CarsWithPartsViewDTO result = getCarWithPartsDTOS();
//        JAXBContext context = JAXBContext.newInstance(CarsWithPartsViewDTO.class);
//        Query 5
//        CustomerPurchasesViewDTO result = getCustomerPurchasesViewDTO();
//        JAXBContext context = JAXBContext.newInstance(CustomerPurchasesViewDTO.class);
//        Query 6
//        SaleFullViewDTO result = getSaleFullViewDTOS();
//        JAXBContext context = JAXBContext.newInstance(SaleFullViewDTO.class);

//        For all queries!
        /*Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(result, System.out);*/
    }

    private CarsWithPartsViewDTO getCarWithPartsDTOS() {
        List<CarsWithPartsDTO> carsWithPartsDTOs = this.carRepository.findAll().stream()
                .map(car -> {
                    CarsWithPartsDTO carView = new CarsWithPartsDTO();
                    carView.setMake(car.getMake());
                    carView.setModel(car.getModel());
                    carView.setTravelledDistance(car.getTravelledDistance());

                    List<PartsDTO> partsDTO = car.getParts().stream()
                            .map(p -> this.mapper.map(p, PartsDTO.class))
                            .collect(Collectors.toList());
                    carView.setParts(partsDTO);

                    return carView;
                })
                .collect(Collectors.toList());
        return new CarsWithPartsViewDTO(carsWithPartsDTOs);
    }

    private CustomerPurchasesViewDTO getCustomerPurchasesViewDTO() {
        List<CustomerViewDTO> result = this.customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerViewDTO customerDTO = new CustomerViewDTO();
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
                .sorted((c1, c2) -> {
                    int cmp = c2.getSpentMoney().compareTo(c1.getSpentMoney());
                    if (cmp == 0) {
                        cmp = c2.getBoughtCars().compareTo(c1.getBoughtCars());
                    }
                    return cmp;
                })
                .collect(Collectors.toList());
        return new CustomerPurchasesViewDTO(result);
    }

    private SaleFullViewDTO getSaleFullViewDTOS() {
        List<SaleDTO> result = this.saleRepository.findAll().stream()
                .map(sale -> {
                    SaleDTO saleDTO = new SaleDTO();
                    saleDTO.setCar(this.mapper.map(sale.getCar(), CarShortDTO.class));
                    saleDTO.setName(sale.getCustomer().getName());
                    saleDTO.setDiscount(sale.getDiscount());

                    saleDTO.setPrice(sale.getCar().getParts().stream()
                            .map(p -> p.getPrice())
                            .reduce(BigDecimal.ZERO, BigDecimal::add));

                    saleDTO.setPriceWithDiscount(saleDTO.getPrice()
                            .multiply(BigDecimal.valueOf(1.0d - saleDTO.getDiscount())));
                    return saleDTO;
                })
                .collect(Collectors.toList());
        return new SaleFullViewDTO(result);
    }

    private CustomerOrderedByBirthDayViewDTO getQueryOne() {
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
                                saleViewDto.setCar(this.mapper.map(sale.getCar(), CarDTO.class));
                                return saleViewDto;
                            })
                            .collect(Collectors.toSet()));
                    return customerDto;
                })
                .collect(Collectors.toList());

        return new CustomerOrderedByBirthDayViewDTO(result);
    }
}
