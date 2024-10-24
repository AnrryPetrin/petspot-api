package com.anrry.petspot.modules.pet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anrry.petspot.modules.pet.Pet;
import com.anrry.petspot.modules.pet.services.PetService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
public class PetController {

  @Autowired
  private PetService petService;

  @GetMapping
  public ResponseEntity<List<Pet>> getAllPets() {
    List<Pet> pets = petService.getAllPets();
    return new ResponseEntity<>(pets, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Pet> getPetById(@PathVariable Integer id) {
    Optional<Pet> pet = petService.getPetById(id);
    return pet.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
    Pet createdPet = petService.createPet(pet);
    return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Pet> updatePet(@PathVariable Integer id, @RequestBody Pet petDetails) {
    try {
      Pet updatedPet = petService.updatePet(id, petDetails);
      return new ResponseEntity<>(updatedPet, HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePet(@PathVariable Integer id) {
    petService.deletePet(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
