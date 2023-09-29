package springdatamodelmapper.demo.dto;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String addressCity;
    private String addressCountry;

    public EmployeeDTO() {
    }

    @Override
    public String toString() {
        return "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressCountry='" + addressCountry + '\'';
    }
    /*@Override
    public String toString(){
        return String.format("%s %s %.2f",
                this.firstName, this.lastName, this.salary);
    }*/

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }
}
