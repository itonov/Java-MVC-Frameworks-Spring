package org.softuni.cardealer.repositories;

import org.softuni.cardealer.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    @Query(value = "SELECT p " +
            "FROM Part AS p " +
            "JOIN p.cars AS pc " +
            "WHERE pc.id = :carId ")
    List<Part> allPartsForCarId(@Param("carId") Long carId);

    Part findByName(String partName);
}
