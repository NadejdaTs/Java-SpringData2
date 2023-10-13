package examPrep.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ImportCustomersDTO {
    @Expose
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate registeredOn;

    private String townName;

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

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }
}
