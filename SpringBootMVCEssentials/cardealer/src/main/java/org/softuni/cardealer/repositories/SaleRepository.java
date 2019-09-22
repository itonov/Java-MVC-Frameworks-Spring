package org.softuni.cardealer.repositories;

import org.softuni.cardealer.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT s FROM Sale AS s WHERE s.discount > 0 ")
    List<Sale> findAllWithDiscount();

    @Query(value = "SELECT s FROM Sale AS s WHERE s.discount = :discount ")
    List<Sale> salesDiscountedBy(@Param(value = "discount") Double discount);
}
