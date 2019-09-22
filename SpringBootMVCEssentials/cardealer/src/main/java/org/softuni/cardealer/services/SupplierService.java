package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Supplier;

import java.util.Map;

public interface SupplierService {
    Map<Supplier, Long> getAllLocalSuppliersAndSupply();

    Map<Supplier, Long> getAllImporterSuppliersAndSupply();
}
