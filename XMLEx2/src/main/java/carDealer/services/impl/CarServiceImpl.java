package carDealer.services.impl;

import carDealer.entities.cars.Car;
import carDealer.entities.cars.CarDTO;
import carDealer.entities.cars.CarsViewDTO;
import carDealer.repositories.CarRepository;
import carDealer.services.api.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public CarsViewDTO findAllByMake(String make) {
        List<Car> cars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make);

        List<CarDTO> carDTOs = cars.stream()
                .map(car -> this.mapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
        return new CarsViewDTO(carDTOs);
    }
}
