package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Part;
import org.softuni.cardealer.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PartServiceImpl implements PartService {
    private PartRepository partRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<Part> allPartsForCarId(Long carId) {

        return this.partRepository.allPartsForCarId(carId);
    }

    @Override
    public void addPart(Part part) {
        this.partRepository.save(part);
    }

    @Override
    public List<Part> allParts() {
        return this.partRepository.findAll();
    }

    @Override
    public void deletePartById(Long partId) {
        this.partRepository.deleteById(partId);
    }

    @Override
    public Part findById(Long partId) {
        return this.partRepository.getOne(partId);
    }

    @Override
    public Part findByName(String partName) {
        return this.partRepository.findByName(partName);
    }
}
