package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Customer;
import org.softuni.cardealer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<Customer> allCustomersOrderedByBirthDateAsc() {
        return this.customerRepository.getAllOrderedByBirthDateAscThenByIsYoungDriverDesc();
    }

    @Override
    public List<Customer> allCustomersOrderedByBirthDateDesc() {
        return this.customerRepository.getAllOrderedByBirthDateDescThenByIsYoungDriverDesc();
    }

    @Override
    public Customer findCustomerById(Long customerId) {
        return this.customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public void addCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }
}
