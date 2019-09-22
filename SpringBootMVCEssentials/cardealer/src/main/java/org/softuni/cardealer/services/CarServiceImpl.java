package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Car;
import org.softuni.cardealer.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAllByMake(String make) {
        return this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make);
    }

    @Override
    public Car findCarById(Long carId) {
        return this.carRepository.findById(carId).orElse(null);
    }

    @Override
    public List<Car> findAllByCustomerId(Long customerId) {
        return this.carRepository.findAllByCustomerId(customerId);
    }

    @Override
    public List<Car> allCars() {
        return this.carRepository.findAll();
    }

    @Override
    public void addCar(Car car) {
        this.carRepository.save(car);
    }
}
