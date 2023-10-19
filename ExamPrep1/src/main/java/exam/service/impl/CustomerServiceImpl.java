package exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exam.model.customers.Customer;
import exam.model.towns.Town;
import exam.model.customers.ImportCustomersDTO;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final TownRepository townRepository;
    private final Path customersPath = Path.of("src", "main", "resources", "files", "json", "customers.json");

    private final Gson gson;
    private ModelMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TownRepository townRepository) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return String.join(System.lineSeparator(), Files.readAllLines(customersPath));
    }

    @Override
    public String importCustomers() throws IOException {
        FileReader reader = new FileReader(customersPath.toAbsolutePath().toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Converter<String, LocalDate> toLocalDate = ctx -> ctx.getSource() != null ? LocalDate.parse(ctx.getSource(), formatter) : null;

        TypeMap<ImportCustomersDTO, Customer> typeMapDate = this.mapper.createTypeMap(ImportCustomersDTO.class, Customer.class);

        ImportCustomersDTO[] importCustomersDTOS = this.gson.fromJson(reader, ImportCustomersDTO[].class);
        List<Customer> customers = Arrays.stream(importCustomersDTOS)
                .map(custDTO -> {
                    Optional<Town> town = this.townRepository.findByName(custDTO.getTown().getName());

                    Customer customer = typeMapDate.addMappings(m -> m
                                    .using(toLocalDate)
                                    .map(src -> src.getRegisteredOn(),
                                            Customer::setRegisteredOn))
                            .map(custDTO);
                    customer.setTown(town.get());

                    return customer;
                })
                .collect(Collectors.toList());

        this.customerRepository.saveAll(customers);
        return "Successfully imported customers!";
    }

}
