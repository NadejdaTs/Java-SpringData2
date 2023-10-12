package carDealer.entities.cars;

import carDealer.entities.parts.PartSimpleDTO;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CarWithPartsDTO {
    private CarShortDTO car;
    private Set<PartSimpleDTO> parts;

    public CarWithPartsDTO() {}

    public CarWithPartsDTO(CarShortDTO car) {
        this.car = car;
        this.parts = new HashSet<>();
    }

    public CarShortDTO getCar() {
        return car;
    }

    public Set<PartSimpleDTO> getParts() {
        return Collections.unmodifiableSet(parts);
    }

    public void setCar(CarShortDTO car) {
        this.car = car;
    }

    public void setParts(Set<PartSimpleDTO> parts) {
        this.parts = parts;
    }
}
