package org.softuni.residentevil.repositories;

import org.softuni.residentevil.entities.Virus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VirusRepository extends JpaRepository<Virus, Long> {

    @Query(value = "SELECT v FROM Virus AS v WHERE v.id =:id")
    Virus findOneById(@Param(value = "id") Long id);
}
