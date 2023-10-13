package examPrep.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import examPrep.model.*;
import examPrep.repository.CustomerRepository;
import examPrep.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Path customersPath = Path.of("src", "main", "resources", "files", "json", "customers.json");

    private final Gson gson;
    private ModelMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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


        Customer[] customers1 = this.gson.fromJson(reader, Customer[].class);

//        ImportCustomerTownNameDTO
        ImportCustomersDTO[] importCustomersDTOS = this.gson.fromJson(reader, ImportCustomersDTO[].class);
        List<Customer> customers = Arrays.stream(importCustomersDTOS)
                .map(custDTO -> this.mapper.map(custDTO, Customer.class))
                .collect(Collectors.toList());

        this.customerRepository.saveAll(customers);
        return "This is";

        //customerJsonDtos.add(addressJsonDtoBulgaria);

        //String result1 = this.gson.fromJson(customerJsonDtos);

/*
        for (ImportShopDTO sh : shops.getShops()) {
            if(sh.isValid()){
                Optional<Shop> optShop = this.shopRepository.findByName(sh.getName());
                if(optShop.isPresent()){
                    result.add("Shop already exist");
                }else{
                    Shop shop = this.mapper.map(sh, Shop.class);
                    this.shopRepository.save(shop);
                    result.add("Saved shop " + shop.getName());
                }
            }else{
                result.add("Invalid shop");
            }
        }
        */

//        return String.join(System.lineSeparator(), result);
    }

}
