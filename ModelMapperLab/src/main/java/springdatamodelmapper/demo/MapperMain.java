package springdatamodelmapper.demo;

import springdatamodelmapper.demo.dto.EmployeeDTO;
import springdatamodelmapper.demo.dto.ManagerDTO;
import springdatamodelmapper.demo.entities.Address;
import springdatamodelmapper.demo.entities.Employee;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MapperMain {
    public static void main(String[] args) {
        ModelMapper mapper = new ModelMapper();
        Address address = new Address("Odrin", 255, "Sofia", "Bulgaria");
        Employee manager = new Employee("Lily1", "Petrova1", BigDecimal.ZERO, LocalDate.now(), address, true);
        Employee first = new Employee("Lily2", "Petrova2", BigDecimal.ONE, LocalDate.now(), address, true);
        Employee second = new Employee("Lily3", "Petrova3", BigDecimal.TEN, LocalDate.now(), address, false);

        manager.addEmployee(first);
        manager.addEmployee(second);

        ManagerDTO dtoManager = mapper.map(manager, ManagerDTO.class);
       //EmployeeDTO dto = mapper.map(first, EmployeeDTO.class);

        System.out.println(dtoManager);
        //System.out.println(dto);
    }
}
