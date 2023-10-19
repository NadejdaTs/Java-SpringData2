package softuni.exam.models.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;

public class ImportAgentDTO {
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    private String town;
    private String email;

    public ImportAgentDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTown() {
        return town;
    }

    public String getEmail() {
        return email;
    }

    public boolean validate(){
        if(!this.email.contains("@") && this.email.contains(".")){
            return false;
        }
        return true;
    }
}
