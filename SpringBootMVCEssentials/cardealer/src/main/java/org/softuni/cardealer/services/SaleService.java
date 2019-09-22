package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Sale;

import java.util.List;

public interface SaleService {
    List<Sale> allSales();

    Sale findSale(Long saleId);

    List<Sale> discountedSales();

    List<Sale> salesDiscountedBy(Double discount);
}
