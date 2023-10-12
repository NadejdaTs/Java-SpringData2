package carDealer.services;

import carDealer.entities.customers.Customer;
import carDealer.entities.customers.CustomerAllDTO;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllOrderByBirthDateIsYoungDriver();
    List<CustomerAllDTO> findAllOrderedCustomerByBirthDateAndIsYoungDriver();
}
