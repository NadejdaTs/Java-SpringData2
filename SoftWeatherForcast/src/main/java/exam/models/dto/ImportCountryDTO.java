package exam.models.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;

public class ImportCountryDTO {
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 60)
    private String countryName;

    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String currency;

    public String getCountryName() {
        return countryName;
    }

    public String getCurrency() {
        return currency;
    }
}
