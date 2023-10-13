package football.models.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ImportTownDTO {
    @Size(min = 2)
    private String name;

    @Positive
    private int population;
    @Size(min = 10)
    private String travelGuide;

    public ImportTownDTO() {}

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

}
