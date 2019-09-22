package org.softuni.residentevil.services;

import org.softuni.residentevil.entities.Virus;

import java.util.List;

public interface VirusService {
    void saveVirus(Virus virus);

    List<Virus> findAll();

    Virus findOneById(Long id);

    void delete(Virus virusToDelete);
}
