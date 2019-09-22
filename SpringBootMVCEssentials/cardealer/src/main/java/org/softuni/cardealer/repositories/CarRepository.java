package org.softuni.cardealer.repositories;

import org.softuni.cardealer.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

    @Query(value = "SELECT c " +
            "FROM Car AS c " +
            "JOIN Sale AS s " +
            "ON c.id = s.car.id " +
            "WHERE s.customer.id = :customerId ")
    List<Car> findAllByCustomerId(@Param("customerId") Long customerId);
}
