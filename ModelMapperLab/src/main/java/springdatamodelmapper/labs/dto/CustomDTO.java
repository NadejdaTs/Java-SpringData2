package springdatamodelmapper.labs.dto;

import springdatamodelmapper.labs.entities.Employee;

import java.math.BigDecimal;

public class CustomDTO {
    private String firstName;
    private String lastName;
    private int managerLastNameLength;

    /* If there are many fields, it will be annoying
    public CustomDTO(Employee employee){
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.managerLastNameLength = employee.getManager() == null ? 0 : employee.getManager().getLastName().length();
    }*/

    public CustomDTO(String firstName, String lastName, int managerLastNameLength) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.managerLastNameLength = managerLastNameLength;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setManagerLastNameLength(int managerLastNameLength) {
        this.managerLastNameLength = managerLastNameLength;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", managerLastNameLength='" + managerLastNameLength + '\'' +
                '}';
    }
}
