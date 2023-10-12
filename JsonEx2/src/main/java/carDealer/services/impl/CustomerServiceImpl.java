package carDealer.services.impl;

import carDealer.entities.customers.Customer;
import carDealer.entities.customers.CustomerAllDTO;
import carDealer.repositories.CustomerRepository;
import carDealer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAllOrderByBirthDateIsYoungDriver() {
        return this.customerRepository.findAllByBirthDate();
    }
}
