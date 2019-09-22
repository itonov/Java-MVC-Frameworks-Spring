package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> allCustomersOrderedByBirthDateAsc();

    List<Customer> allCustomersOrderedByBirthDateDesc();

    Customer findCustomerById(Long customerId);

    void addCustomer(Customer customer);
}
