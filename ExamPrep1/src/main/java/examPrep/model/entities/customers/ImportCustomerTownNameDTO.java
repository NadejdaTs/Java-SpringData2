package examPrep.model.entities.customers;

public class ImportCustomerTownNameDTO {
    private String name;

    public ImportCustomerTownNameDTO() {}

    public ImportCustomerTownNameDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
