package springdatamodelmapper.labs;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import springdatamodelmapper.labs.entities.dto.CustomDTO;
import springdatamodelmapper.labs.entities.dto.EmployeeSpringDTO;
import springdatamodelmapper.labs.entities.Employee;
import springdatamodelmapper.labs.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private EmployeeService employeeService;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.persist();
        /*Optional<Employee> managerOp = this.employeeService.findOneById(2);
        Employee manager = managerOp.get();

        ModelMapper mapper = new ModelMapper();
        EmployeeSpringDTO dto = mapper.map(manager, EmployeeSpringDTO.class);
        System.out.println(dto);*/

//        when use List<Employee> as return type
        /*List<Employee> listOfEmployees = this.employeeService.findEmployeesBornBefore(1990);
        ModelMapper mapper = new ModelMapper();

        listOfEmployees.stream()
                .map(e -> mapper.map(e, EmployeeSpringDTO.class))
                .forEach(System.out::println);*/

//      when use List<EmployeeSpringDTO> as return type
//      it should work, but not here
        /*this.employeeService.findEmployeesBornBefore(1991)
                .forEach(System.out::println);*/

        List<Employee> all = this.employeeService.findAll();

        ModelMapper mapper = new ModelMapper();
        TypeMap<Employee, CustomDTO> employeeToCustom =
                mapper.createTypeMap(Employee.class, CustomDTO.class);

        Converter<Employee, Integer> getLastNameLength =
                ctx -> ctx.getSource() == null
                        ? null
                        : ctx.getSource().getLastName().length();

        employeeToCustom.addMappings(mapping ->
                mapping.when(Objects::nonNull)
                        .using(getLastNameLength)
                        .map(Employee::getManager, CustomDTO::setManagerLastNameLength));

        all.stream()
                .map(employeeToCustom::map)
                .forEach(System.out::println);
    }

    private void persist() {
        Employee manager = new Employee("Mrs.", "Manager",
                BigDecimal.valueOf(5000), LocalDate.now(), null);

        Employee firstEmployee = new Employee("first", "second",
                BigDecimal.TEN, LocalDate.now(), manager);
        this.employeeService.save(firstEmployee);

        //if we don`t say get() -  it will take Optional<Employee>
        manager = this.employeeService
                .findOneById(firstEmployee.getManager().getId())
                .get();

        Employee secEmployee = new Employee("second", "last",
                BigDecimal.TEN, LocalDate.now(), manager);
        this.employeeService.save(secEmployee);
    }
}
