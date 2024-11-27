package com.anrry.petspot.modules.vaccine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anrry.petspot.modules.pet.Enums;

import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @GetMapping
    public ResponseEntity<List<Vaccine>> getAllVaccines() {
        List<Vaccine> vaccines = vaccineService.getAllVaccines();
        return new ResponseEntity<>(vaccines, HttpStatus.OK);
    }

    @GetMapping("/species/{species}")
    public ResponseEntity<List<Vaccine>> getVaccinesBySpecies(@PathVariable Enums.Species species) {
        List<Vaccine> vaccines = vaccineService.getVaccinesBySpecies(species);
        return new ResponseEntity<>(vaccines, HttpStatus.OK);
    }
}
