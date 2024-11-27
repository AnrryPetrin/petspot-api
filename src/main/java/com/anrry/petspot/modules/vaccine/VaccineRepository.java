package com.anrry.petspot.modules.vaccine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anrry.petspot.modules.pet.Enums;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
    List<Vaccine> findBySpecies(Enums.Species species);
}
