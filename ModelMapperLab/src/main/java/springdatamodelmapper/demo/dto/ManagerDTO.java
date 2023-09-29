package springdatamodelmapper.demo.dto;

import java.util.Set;
import java.util.stream.Collectors;

public class ManagerDTO {
    private String firstName;
    private String lastName;
    private Set<EmployeeDTO> subordinates;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSubordinates(Set<EmployeeDTO> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString(){
        String empl = this.subordinates.stream()
                .map(EmployeeDTO::toString)
                .map(s -> "\t- " + s)
                .collect(Collectors.joining(System.lineSeparator()));

        return String.format("%s %s | Employees: %d%n%s%n",
                this.firstName, this.lastName, this.subordinates.size(), empl);
    }
}
