package org.softuni.cardealer.controllers;

import org.softuni.cardealer.entities.Car;
import org.softuni.cardealer.entities.Part;
import org.softuni.cardealer.services.CarService;
import org.softuni.cardealer.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class CarController {
    private CarService carService;

    private PartService partService;

    @Autowired
    public CarController(CarService carService, PartService partService) {
        this.carService = carService;
        this.partService = partService;
    }

    @GetMapping("/cars/{make}")
    public String showCarsFromMake(@PathVariable(name = "make") String make, Model model) {
        List<Car> cars = this.carService.findAllByMake(make);

        model.addAttribute("cars", cars);

        return "/cars/all";
    }

    @GetMapping("/cars/{id}/parts")
    public String showAllCarsWithTheirParts(@PathVariable(name = "id") Long carId, Model model) {
        Car neededCar = this.carService.findCarById(carId);
        List<Part> parts = this.partService.allPartsForCarId(carId);

        model.addAttribute("car", neededCar);
        model.addAttribute("parts", parts);

        return "/cars/all-with-parts";
    }

    @GetMapping("/cars/all")
    public String allCars(Model model) {
        model.addAttribute("cars", this.carService.allCars());

        return "/cars/all";
    }

    @GetMapping("/cars/add")
    public String addCar(HttpServletRequest request, Model model) {
        if (request.getSession() == null) {
            return "redirect:/";
        }

        List<Part> allParts = this.partService.allParts();

        model.addAttribute("parts", allParts);

        return "/cars/add";
    }

    @PostMapping("/cars/add")
    public String addCarConfirm(HttpServletRequest request) {
        if (request.getSession() == null) {
            return "redirect:/";
        }

        Car carToAdd = new Car();

        carToAdd.setId((long) this.carService.allCars().size());
        carToAdd.setMake(request.getParameter("make"));
        carToAdd.setModel(request.getParameter("model"));
        carToAdd.setTravelledDistance(Long.valueOf(request.getParameter("distance")));

        Arrays.stream(request.getParameterValues("chosenParts"))
                .map(x -> this.partService.findByName(x))
                .forEach(x -> {
                    x.addCar(carToAdd);
                    this.partService.addPart(x);
                });

        this.carService.addCar(carToAdd);

        return "redirect:/cars/all";
    }
}
