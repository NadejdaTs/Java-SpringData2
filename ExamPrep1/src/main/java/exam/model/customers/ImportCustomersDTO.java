package exam.model.customers;

import com.google.gson.annotations.Expose;

public class ImportCustomersDTO {
    @Expose
    private String firstName;
    private String lastName;
    private String email;
    private String registeredOn;

    private ImportCustomerTownNameDTO town;

    public ImportCustomersDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public ImportCustomerTownNameDTO getTown() {
        return town;
    }
}
