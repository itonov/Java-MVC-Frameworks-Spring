package org.softuni.cardealer.controllers;

import org.softuni.cardealer.entities.Car;
import org.softuni.cardealer.entities.Customer;
import org.softuni.cardealer.services.CarService;
import org.softuni.cardealer.services.CustomerService;
import org.softuni.cardealer.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerController {
    private CustomerService customerService;

    private CarService carService;

    private PartService partService;

    @Autowired
    public CustomerController(CustomerService customerService, CarService carService, PartService partService) {
        this.customerService = customerService;
        this.carService = carService;
        this.partService = partService;
    }

    @GetMapping("/customers/all/ascending")
    public String orderedCustomersByDateAsc(Model model) {
        List<Customer> customers = this.customerService.allCustomersOrderedByBirthDateAsc();

        model.addAttribute("customers", customers);
        return "customers/all";
    }

    @GetMapping("/customers/all/descending")
    public String orderedCustomersByDateDesc(Model model) {
        List<Customer> customers = this.customerService.allCustomersOrderedByBirthDateDesc();

        model.addAttribute("customers", customers);
        return "customers/all";
    }

    @GetMapping("/customers/{id}")
    public String totalSalesByCustomer(@PathVariable(name = "id") Long customerId, Model model) {
        String neededCustomerName = this.customerService.findCustomerById(customerId).getName();

        List<Car> boughtCars = this.carService.findAllByCustomerId(customerId);

        int boughtCarsCount = boughtCars.size();

        double totalSpentMoney = boughtCars
                .stream()
                .mapToDouble(x -> {
                    double result = this.partService.allPartsForCarId(x.getId())
                            .stream()
                            .mapToDouble(p -> p.getPrice())
                            .sum();
                    return result;
                })
                .sum();

        model.addAttribute("customerName", neededCustomerName);
        model.addAttribute("carsCount", boughtCarsCount);
        model.addAttribute("totalSpentMoney", totalSpentMoney);

        return "/customers/sales-by-customer";
    }

    @GetMapping("/customers/add")
    public String addCustomer() {
        return "/customers/add";
    }

    @PostMapping("/customers/add")
    public String addCustomerConfirm(HttpServletRequest request, Model model) throws ParseException {
        String customerName = request.getParameter("name");
        String customerBirthDateAsString = request.getParameter("date");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date customerBirthDate = formatter.parse(customerBirthDateAsString);

        Customer newCustomer = new Customer();
        newCustomer.setBirthDate(customerBirthDate);
        newCustomer.setName(customerName);

        LocalDate localDate = customerBirthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int birthYear = localDate.getYear();

        int currentYear = LocalDate.now().getYear();

        if (currentYear - birthYear < 2) {
            newCustomer.setYoungDriver(true);
        } else {
            newCustomer.setYoungDriver(false);
        }

        Long id = (long) (this.customerService.allCustomersOrderedByBirthDateDesc().size() + 1);
        newCustomer.setId(id);

        this.customerService.addCustomer(newCustomer);

        return "redirect:/customers/all/descending";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "customers/edit";
    }

    @PostMapping("/customers/edit/{id}")
    public String editCustomerConfirm(@PathVariable("id") Long id, HttpServletRequest request, Model model) throws ParseException {
        Customer customerToEdit = this.customerService.findCustomerById(id);
        customerToEdit.setName(request.getParameter("name"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date customerBirthDate = formatter.parse(request.getParameter("date"));

        customerToEdit.setBirthDate(customerBirthDate);

        this.customerService.addCustomer(customerToEdit);

        return "redirect:/customers/all/descending";
    }
}
