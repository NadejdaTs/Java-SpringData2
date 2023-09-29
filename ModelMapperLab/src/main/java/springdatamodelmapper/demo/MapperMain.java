package springdatamodelmapper.demo;

import springdatamodelmapper.demo.dto.EmployeeDTO;
import springdatamodelmapper.demo.dto.ManagerDTO;
import springdatamodelmapper.demo.entities.Address;
import springdatamodelmapper.demo.entities.Employee;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MapperMain {
    public static void main(String[] args) {
        ModelMapper mapper = new ModelMapper();
//        01
        Address address = new Address("Odrin", 255, "Sofia", "Bulgaria");
        Employee manager = new Employee("Lily1", "Petrova1", BigDecimal.ZERO, LocalDate.now(), address, true);
        Employee first = new Employee("Lily2", "Petrova2", BigDecimal.ONE, LocalDate.now(), address, true);
        Employee second = new Employee("Lily3", "Petrova3", BigDecimal.TEN, LocalDate.now(), address, false);

        manager.addEmployee(first);
        manager.addEmployee(second);

        ManagerDTO dtoManager = mapper.map(manager, ManagerDTO.class);
//        EmployeeDTO dto = mapper.map(first, EmployeeDTO.class);

//        System.out.println(dtoManager);
//        System.out.println(dto);

//        02
        /*Employee manager2 = new Employee("Katq", "Stoqnova", BigDecimal.ZERO, LocalDate.now(), address, true);
        Employee first2 = new Employee("Todorka", "Milkova", BigDecimal.ONE, LocalDate.now(), address, true);
        Employee second2 = new Employee("Milka", "Todorkova", BigDecimal.TEN, LocalDate.now(), address, false);

        manager2.addEmployee(first2);
        manager2.addEmployee(second2);
        ManagerDTO dtoManager2 = mapper.map(manager2, ManagerDTO.class);

        List<ManagerDTO> listOfManagers = new ArrayList<>();
        listOfManagers.add(dtoManager);
        listOfManagers.add(dtoManager2);

        listOfManagers.stream()
                .forEach(System.out::println);*/
    }
}
