package org.softuni.cardealer.controllers;

import org.softuni.cardealer.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupplierController {
    private SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/suppliers/local")
    public String allLocalSuppliers(Model model) {
        model.addAttribute("suppliers", this.supplierService.getAllLocalSuppliersAndSupply());

        return "suppliers/all";
    }

    @GetMapping("/suppliers/importers")
    public String allImporterSuppliers(Model model) {
        model.addAttribute("suppliers", this.supplierService.getAllImporterSuppliersAndSupply());

        return "all";
    }
}
