package org.softuni.cardealer.repositories;

import org.softuni.cardealer.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query(value = "SELECT s, COUNT(p) " +
            "FROM Supplier AS s " +
            "JOIN Part AS p " +
            "ON s.id = p.supplier " +
            "WHERE s.importer = false " +
            "GROUP BY s")
    List<Object[]> findAllByImporterFalse();

    @Query(value = "SELECT s, COUNT(p) " +
            "FROM Supplier AS s " +
            "JOIN Part AS p " +
            "ON s.id = p.supplier " +
            "WHERE s.importer = true " +
            "GROUP BY s")
    List<Object[]> findAllByImporterTrue();
}
