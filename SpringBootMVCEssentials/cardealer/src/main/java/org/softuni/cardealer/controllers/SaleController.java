package org.softuni.cardealer.controllers;

import org.softuni.cardealer.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SaleController {
    private SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/sales")
    public String allSales(Model model) {
        model.addAttribute("sales", this.saleService.allSales());

        return "/sales/all";
    }

    @GetMapping("/sales/{id}")
    public String saleDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("sale", this.saleService.findSale(id));

        return "/sales/sale-details";
    }

    @GetMapping("/sales/discounted")
    public String discountedSales(Model model) {
        model.addAttribute("sales", this.saleService.discountedSales());

        return "sales/all";
    }

    @GetMapping("/sales/discounted/{percent}")
    public String salesDiscountedBy(@PathVariable("percent") Double percent, Model model) {
        model.addAttribute("sales", this.saleService.salesDiscountedBy(percent));

        return "sales/all";
    }
}
