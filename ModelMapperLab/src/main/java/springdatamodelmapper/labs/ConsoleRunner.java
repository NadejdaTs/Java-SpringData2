package springdatamodelmapper.labs;

import springdatamodelmapper.labs.dto.CustomDTO;
import springdatamodelmapper.labs.dto.EmployeeSpringDTO;
import springdatamodelmapper.labs.entities.Employee;
import springdatamodelmapper.labs.services.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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
        //this.persist();
        /*Optional<Employee> managerOp = this.employeeService.findOneById(2);
        Employee manager = managerOp.get();

        ModelMapper mapper = new ModelMapper();
        EmployeeSpringDTO dto = mapper.map(manager, EmployeeSpringDTO.class);
        System.out.println(dto);*/

        /*List<Employee> listOfEmployees = this.employeeService.findEmployeesBornBefore(1990);
        ModelMapper mapper = new ModelMapper();

        listOfEmployees.stream()
                .map(e -> mapper.map(e, EmployeeSpringDTO.class))
                .forEach(System.out::println);*/
        /*this.employeeService.findEmployeesBornBefore(1990)
                .forEach(System.out::println);*/
        this.employeeService.findEmployeesBornBefore(1990)
                .forEach(System.out::println);
        List<Employee> all = this.employeeService.findAll();

        ModelMapper mapper = new ModelMapper();
        TypeMap<Employee, CustomDTO> employeeToCustom = mapper.createTypeMap(Employee.class, CustomDTO.class);

        Converter<Employee, Integer> getLastNameLength = ctx -> ctx.getSource() == null ? null : ctx.getSource().getLastName().length();

        employeeToCustom.addMappings(mapping ->
                mapping.when(Objects::nonNull)
                        .using(getLastNameLength)
                        .map(Employee::getManager, CustomDTO::setManagerLastNameLength));

        all.stream()
                .map(employeeToCustom::map)
                .forEach(System.out::println);
    }

    private void persist() {
        Employee manager = new Employee("Mrs.", "Manager", BigDecimal.valueOf(5000), LocalDate.now(), null);
        Employee firstEmployee = new Employee("first", "last", BigDecimal.TEN, LocalDate.now(), manager);
        Employee secEmployee = new Employee("first", "last", BigDecimal.TEN, LocalDate.now(), manager);

        this.employeeService.save(firstEmployee);
        Employee employee = this.employeeService
                .findOneById(firstEmployee.getManager().getId())
                .get(); //if we don`t say get() -  it will take Optional<Employee>
        this.employeeService.save(secEmployee);
    }
}
