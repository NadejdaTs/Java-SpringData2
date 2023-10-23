package exam.models.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class ImportCityDTO {

    @Size(min = 2, max = 60)
    private String cityName;
    @Size(min = 2)
    private String description;
    @Min(500L)
    private Long population;
    @Column(nullable = false)
    private Long country;

    public String getCityName() {
        return cityName;
    }

    public String getDescription() {
        return description;
    }

    public Long getPopulation() {
        return population;
    }

    public Long getCountry() {
        return country;
    }

}
