package org.softuni.residentevil.controllers;

import org.softuni.residentevil.entities.Capital;
import org.softuni.residentevil.entities.Magnitude;
import org.softuni.residentevil.entities.Mutation;
import org.softuni.residentevil.entities.Virus;
import org.softuni.residentevil.models.binding.VirusAddDto;
import org.softuni.residentevil.models.binding.VirusEditDto;
import org.softuni.residentevil.models.output.VirusShowDto;
import org.softuni.residentevil.services.CapitalService;
import org.softuni.residentevil.services.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VirusController {
    private VirusService virusService;

    private CapitalService capitalService;

    @Autowired
    public VirusController(VirusService virusService, CapitalService capitalService) {
        this.virusService = virusService;
        this.capitalService = capitalService;
    }

    @GetMapping("/viruses/add")
    public String addVirus(@ModelAttribute(name = "virusAddDto") VirusAddDto virusAddDto, Model model) {
        List<Capital> allCapitals = this.capitalService.findAll();

        model.addAttribute("allCapitals", allCapitals);

        return "viruses/add";
    }

    @PostMapping("viruses/add")
    public String addVirusConfirm(@Valid VirusAddDto virusModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Capital> allCapitals = this.capitalService.findAll();

            model.addAttribute("allCapitals", allCapitals);
            return "viruses/add";
        }

        List<Capital> affectedCapitals = this.capitalService.findAll()
                .stream()
                .filter(c -> virusModel.getAffectedCapitals().contains(c.getName()))
                .collect(Collectors.toList());

        Virus newVirus = new Virus();

        newVirus.setCapitals(affectedCapitals);
        newVirus.setCreator(virusModel.getCreator());
        newVirus.setDescription(virusModel.getDescription());
        newVirus.setHoursUntilTurn(virusModel.getHoursUntilTurn());
        newVirus.setMagnitude(Magnitude.valueOf(virusModel.getMagnitude()));
        newVirus.setMutation(Mutation.valueOf(virusModel.getMutation()));
        newVirus.setName(virusModel.getName());
        newVirus.setReleasedOn(virusModel.getReleaseDate());
        newVirus.setSideEffect(virusModel.getSideEffect());
        newVirus.setTurnoverRate(virusModel.getTurnOverRate());

        if (virusModel.getCurable() == null) {
            newVirus.setCurable(false);
        } else {
            newVirus.setCurable(virusModel.getCurable());
        }

        if (virusModel.getDeadly() == null) {
            newVirus.setDeadly(false);
        } else {
            newVirus.setDeadly(virusModel.getDeadly());
        }

        this.virusService.saveVirus(newVirus);

        return "redirect:/";
    }

    @GetMapping("/viruses/show")
    public String showViruses(Model model) {
        List<Virus> allViruses = this.virusService.findAll();
        List<VirusShowDto> virusesToShow = new ArrayList<>();

        allViruses.forEach(v -> {
            VirusShowDto virusShowDto = new VirusShowDto();

            virusShowDto.setId(v.getId());
            virusShowDto.setMagnitude(v.getMagnitude().toString());
            virusShowDto.setName(v.getName());
            virusShowDto.setReleasedOn(v.getReleasedOn());

            virusesToShow.add(virusShowDto);
        });

        model.addAttribute("viruses", virusesToShow);

        return "viruses/all";
    }

    @GetMapping("/viruses/edit/{id}")
    public String editVirus(@PathVariable(name = "id") String virusId,
                            @ModelAttribute(name = "virusEditDto") VirusEditDto virusEditDto,
                            Model model) {
        Virus virusToEdit = this.virusService.findOneById(Long.valueOf(virusId));

        if (virusToEdit == null) {
            return "redirect:/viruses/all";
        }

        List<Capital> allCapitals = this.capitalService.findAll();

        model.addAttribute("virusName", virusToEdit.getName());
        model.addAttribute("capitals", allCapitals);
        model.addAttribute("virusId", virusId);

        return "viruses/edit";
    }

    @PostMapping("viruses/edit/{id}")
    public String editVirusConfirm(@PathVariable(name = "id") String virusId,
                                   @Valid VirusEditDto virusEditDto,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            List<Capital> allCapitals = this.capitalService.findAll();
            String virusName = this.virusService.findOneById(Long.valueOf(virusId)).getName();

            model.addAttribute("capitals", allCapitals);
            model.addAttribute("virusName", virusName);
            return "viruses/edit";
        }

        List<Capital> affectedCapitals = this.capitalService.findAll()
                .stream()
                .filter(c -> virusEditDto.getAffectedCapitals().contains(c.getName()))
                .collect(Collectors.toList());

        Virus virusToEdit = this.virusService.findOneById(Long.valueOf(virusId));

        virusToEdit.setCapitals(affectedCapitals);
        virusToEdit.setCreator(virusEditDto.getCreator());
        virusToEdit.setDescription(virusEditDto.getDescription());
        virusToEdit.setHoursUntilTurn(virusEditDto.getHoursUntilTurn());
        virusToEdit.setMagnitude(Magnitude.valueOf(virusEditDto.getMagnitude()));
        virusToEdit.setMutation(Mutation.valueOf(virusEditDto.getMutation()));
        virusToEdit.setName(virusEditDto.getName());
        virusToEdit.setSideEffect(virusEditDto.getSideEffect());
        virusToEdit.setTurnoverRate(virusEditDto.getTurnOverRate());

        if (virusEditDto.getCurable() == null) {
            virusToEdit.setCurable(false);
        } else {
            virusToEdit.setCurable(virusEditDto.getCurable());
        }

        if (virusEditDto.getDeadly() == null) {
            virusToEdit.setDeadly(false);
        } else {
            virusToEdit.setDeadly(virusEditDto.getDeadly());
        }

        this.virusService.saveVirus(virusToEdit);

        return "redirect:/";
    }

    @GetMapping("/viruses/delete/{id}")
    public String virusDeleteConfirm(@PathVariable(name = "id") String virusId) {
        Virus virusToDelete = this.virusService.findOneById(Long.valueOf(virusId));

        if (virusToDelete == null) {
            return "redirect:/viruses/all";
        }

        this.virusService.delete(virusToDelete);

        return "redirect:/viruses/show";
    }
}
