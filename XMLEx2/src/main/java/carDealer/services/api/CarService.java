package carDealer.services.api;

import carDealer.entities.cars.CarsViewDTO;

import java.util.List;

public interface CarService {
    CarsViewDTO findAllByMake(String make);
}
