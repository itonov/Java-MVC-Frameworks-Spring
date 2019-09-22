package org.softuni.cardealer.entities;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {
    private Long id;

    private Double discount;

    private Car car;

    private Customer customer;

    public Sale() {
    }

    @Id
    @Column(nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "discount")
    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @OneToOne(targetEntity = Car.class)
    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne(targetEntity = Customer.class)
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
