package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Sale;
import org.softuni.cardealer.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    private SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Sale> allSales() {
        return this.saleRepository.findAll();
    }

    @Override
    public Sale findSale(Long saleId) {
        return this.saleRepository.getOne(saleId);
    }

    @Override
    public List<Sale> discountedSales() {
        return this.saleRepository.findAllWithDiscount();
    }

    @Override
    public List<Sale> salesDiscountedBy(Double discount) {
        return this.saleRepository.salesDiscountedBy(discount);
    }
}
