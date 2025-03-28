package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {

    final
    CarRepository carRepository;

    public CarManagerService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public Car save(Car oneCar) {
        return carRepository.save(oneCar);
    }

    public List<Car> getAllCars() {

        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long carId) {
        return Optional.of(carRepository.findByCarId(carId) );
    }
}
