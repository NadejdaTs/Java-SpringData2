package carDealer.services.api;

import carDealer.entities.customers.Customer;
import carDealer.entities.customers.CustomerAllDTO;
import carDealer.entities.customers.CustomerAllViewDTO;

import java.util.List;

public interface CustomerService {
    CustomerAllViewDTO findAllOrderedCustomerByBirthDateAndIsYoungDriver();

    List<Customer> findAllOrderByBirthDateIsYoungDriver();
}
