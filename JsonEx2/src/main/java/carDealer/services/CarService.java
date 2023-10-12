package carDealer.services;

import carDealer.entities.cars.Car;
import carDealer.entities.cars.CarDTO;
import carDealer.entities.cars.CarViewDTO;
import carDealer.entities.cars.CarWithPartsDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> findAllByMake(String make);

    List<Car> findCarsAndPartsInfo(String make);
}
