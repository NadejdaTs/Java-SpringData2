package softuni.exam.models.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class ImportTownDTO {
    @Column(unique = true)
    @Size(min = 2)
    private String townName;
    private Integer population;

    public ImportTownDTO() {}

    /*public ImportTownDTO(String townName, Long population) {
        this.townName = townName;
        this.population = population;
    }*/

    public String getTownName() {
        return townName;
    }

    public Integer getPopulation() {
        return population;
    }
}
