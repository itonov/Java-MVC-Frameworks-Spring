package org.softuni.residentevil.services;

import org.softuni.residentevil.entities.Virus;
import org.softuni.residentevil.repositories.VirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VirusServiceImpl implements VirusService {
    private VirusRepository virusRepository;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository) {
        this.virusRepository = virusRepository;
    }

    @Override
    public void saveVirus(Virus virus) {
        this.virusRepository.save(virus);
    }

    @Override
    public List<Virus> findAll() {
        return this.virusRepository.findAll();
    }

    @Override
    public Virus findOneById(Long id) {
        return this.virusRepository.findOneById(id);
    }

    @Override
    public void delete(Virus virusToDelete) {
        this.virusRepository.delete(virusToDelete);
    }
}
