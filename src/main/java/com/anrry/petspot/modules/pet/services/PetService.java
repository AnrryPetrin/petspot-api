package com.anrry.petspot.modules.pet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anrry.petspot.modules.pet.Pet;
import com.anrry.petspot.modules.pet.PetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

  @Autowired
  private PetRepository petRepository;

  public List<Pet> getAllPets() {
    return petRepository.findAll();
  }

  public Optional<Pet> getPetById(Integer id) {
    return petRepository.findById(id);
  }

  public Pet createPet(Pet pet) {
    return petRepository.save(pet);
  }

  public Pet updatePet(Integer id, Pet petDetails) {
    return petRepository.findById(id).map(pet -> {
      pet.setName(petDetails.getName());
      pet.setSpecies(petDetails.getSpecies());
      pet.setBreed(petDetails.getBreed());
      pet.setWeight(petDetails.getWeight());
      pet.setDateOfBirth(petDetails.getDateOfBirth());
      pet.setGender(petDetails.getGender());
      pet.setIsNeutered(petDetails.getIsNeutered());
      pet.setColor(petDetails.getColor());
      return petRepository.save(pet);
    }).orElseThrow(() -> new RuntimeException("Pet not found with id " + id));
  }

  public void deletePet(Integer id) {
    petRepository.deleteById(id);
  }
}
