package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Supplier;
import org.softuni.cardealer.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Map<Supplier, Long> getAllLocalSuppliersAndSupply() {
        Map<Supplier, Long> result = new HashMap<>();

        for (Object[] object : this.supplierRepository.findAllByImporterFalse()) {
            result.put((Supplier) object[0], (Long)object[1]);
        }

        return result;
    }

    @Override
    public Map<Supplier, Long> getAllImporterSuppliersAndSupply() {
        Map<Supplier, Long> result = new HashMap<>();

        for (Object[] object : this.supplierRepository.findAllByImporterFalse()) {
            result.put((Supplier) object[0], (Long)object[1]);
        }

        return result;
    }
}
