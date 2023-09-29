package springdatamodelmapper.demo.dto;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    //private String addressCity;

    /*@Override
    public String toString() {
        return "EmployeeDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                //", addressCity='" + addressCity + '\'' +
                '}';
    }*/
    @Override
    public String toString(){
        return String.format("%s %s %.2f",
                this.firstName, this.lastName, this.salary);
    }

    /*public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
