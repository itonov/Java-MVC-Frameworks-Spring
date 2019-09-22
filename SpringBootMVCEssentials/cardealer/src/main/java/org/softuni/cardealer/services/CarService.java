package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Car;

import java.util.List;

public interface CarService {
    List<Car> findAllByMake(String make);

    Car findCarById(Long carId);

    List<Car> findAllByCustomerId(Long customerId);

    List<Car> allCars();

    void addCar(Car car);
}
