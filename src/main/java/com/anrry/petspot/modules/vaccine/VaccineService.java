package com.anrry.petspot.modules.vaccine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anrry.petspot.modules.pet.Enums;

import java.util.List;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    public List<Vaccine> getVaccinesBySpecies(Enums.Species species) {
        return vaccineRepository.findBySpecies(species);
    }

    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }
}
