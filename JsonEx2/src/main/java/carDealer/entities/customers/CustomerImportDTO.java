package carDealer.entities.customers;

public class CustomerImportDTO {
    private String name;
    private String birthDate;
    private Boolean isYoungDriver;

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }
}
