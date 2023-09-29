package springdatamodelmapper.labs.repositories;

import springdatamodelmapper.labs.entities.dto.EmployeeSpringDTO;
import springdatamodelmapper.labs.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findById(int id);

    //List<Employee> findByBirthdayBeforeOrderBySalaryDesc(LocalDate beforeYear);
    List<EmployeeSpringDTO> findByBirthdayBeforeOrderBySalaryDesc(LocalDate beforeYear);
}
