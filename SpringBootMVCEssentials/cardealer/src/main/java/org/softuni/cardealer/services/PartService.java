package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Part;

import java.util.List;

public interface PartService {
    List<Part> allPartsForCarId(Long carId);

    void addPart(Part part);

    List<Part> allParts();

    void deletePartById(Long partId);

    Part findById(Long partId);

    Part findByName(String partName);
}
