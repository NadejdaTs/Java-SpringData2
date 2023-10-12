package carDealer.services;

import carDealer.entities.cars.Car;
import carDealer.entities.cars.CarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> findAllByMake(String make);
}
