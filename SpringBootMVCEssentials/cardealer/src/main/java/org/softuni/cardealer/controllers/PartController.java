package org.softuni.cardealer.controllers;

import org.softuni.cardealer.entities.Part;
import org.softuni.cardealer.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PartController {
    private PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/parts/add")
    public String addPart() {

        return "/parts/add";
    }

    @PostMapping("/parts/add")
    public String addPartConfirm(HttpServletRequest request) {
        String partName = request.getParameter("name");
        Long quantity = Long.valueOf(request.getParameter("quantity"));

        if (quantity == null || quantity == 0) {
            quantity = 1L;
        }

        long id = this.partService.allParts().size();

        Part newPart = new Part();

        newPart.setId(id);
        newPart.setName(partName);
        newPart.setQuantity(quantity);

        this.partService.addPart(newPart);

        return "redirect:/parts/all";
    }

    @GetMapping("/parts/all")
    public String allParts(Model model) {
        List<Part> allParts = this.partService.allParts();

        model.addAttribute("parts", allParts);

        return "parts/all";
    }

    @GetMapping("/parts/delete/{id}")
    public String partDelete(@PathVariable(name = "id") Long partId, Model model) {
        Part partToDelete = this.partService.findById(partId);

        model.addAttribute("part", partToDelete);

        return "/parts/delete";
    }

    @PostMapping("/parts/delete/{id}")
    public String partDeleteConfirm(@PathVariable(name = "id") Long partId) {
        this.partService.deletePartById(partId);

        return "redirect:/parts/all";
    }

    @GetMapping("/parts/edit/{id}")
    public String partEdit(@PathVariable("id") Long partId, Model model) {
        Part partToEdit = this.partService.findById(partId);

        model.addAttribute("part", partToEdit);

        return "/parts/edit";
    }

    @PostMapping("/parts/edit/{id}")
    public String partEditConfirm(@PathVariable("id") Long partId, HttpServletRequest request) {
        Part partToEdit = this.partService.findById(partId);

        partToEdit.setQuantity(Long.valueOf(request.getParameter("quantity")));
        partToEdit.setPrice(Double.valueOf(request.getParameter("price")));

        this.partService.addPart(partToEdit);

        return "redirect:/parts/all";
    }
}
