package carDealer.services.impl;

import carDealer.entities.cars.Car;
import carDealer.entities.cars.CarDTO;
import carDealer.entities.cars.CarWithPartsDTO;
import carDealer.repositories.CarRepository;
import carDealer.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDTO> findAllByMake(String make) {
        return this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make);
    }

    @Override
    public List<Car> findCarsAndPartsInfo(String make) {
        return this.carRepository.findByMakeOrderByModelAscTravelledDistanceDesc(make);
    }
}
