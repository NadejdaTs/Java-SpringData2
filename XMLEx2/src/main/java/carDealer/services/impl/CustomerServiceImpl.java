package carDealer.services.impl;

import carDealer.entities.customers.Customer;
import carDealer.entities.customers.CustomerAllDTO;
import carDealer.entities.customers.CustomerAllViewDTO;
import carDealer.repositories.CustomerRepository;
import carDealer.services.api.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private ModelMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public CustomerAllViewDTO findAllOrderedCustomerByBirthDateAndIsYoungDriver() {
        List<Customer> customers = this.customerRepository.findAllOrderedCustomer();

        List<CustomerAllDTO> dtos = customers.stream()
                .map(customer -> this.mapper.map(customer, CustomerAllDTO.class))
                .collect(Collectors.toList());
        return new CustomerAllViewDTO(dtos);
    }

    @Override
    public List<Customer> findAllOrderByBirthDateIsYoungDriver() {
        return this.customerRepository.findAllOrderedCustomer();
    }
}
