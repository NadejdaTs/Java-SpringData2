package springdatamodelmapper.labs.services;

import springdatamodelmapper.labs.dto.EmployeeSpringDTO;
import springdatamodelmapper.labs.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findOneById(int id);
    void save(Employee employee);

    List<EmployeeSpringDTO> findEmployeesBornBefore(int i);
    List<Employee> findAll();
}
