package com.anrry.petspot.modules.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anrry.petspot.modules.vaccine.Vaccine;
import com.anrry.petspot.modules.vaccine.VaccineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

  @Autowired
  private PetRepository petRepository;

  @Autowired
  private VaccineRepository vaccineRepository;

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
    if (petRepository.existsById(id)) {
      petRepository.deleteById(id);
    } else {
      throw new RuntimeException("Pet not found with id " + id);
    }
  }

  public Pet addVaccinesToPet(Integer petId, List<Integer> vaccineIds) {
    Pet pet = petRepository.findById(petId)
        .orElseThrow(() -> new RuntimeException("Pet not found with id " + petId));

    List<Vaccine> vaccines = vaccineRepository.findAllById(vaccineIds);

    if (vaccines.isEmpty()) {
      throw new RuntimeException("No valid vaccines found for the given IDs");
    }

    for (Vaccine vaccine : vaccines) {
      if (!pet.getVaccines().contains(vaccine)) {
        pet.getVaccines().add(vaccine);
      }
    }

    return petRepository.save(pet);
  }

}
