package com.anrry.petspot.modules.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

  // Custom query to find pets by species
  List<Pet> findBySpecies(Enums.Species species);

  // Custom query to find pets by owner's ID
  List<Pet> findByOwnerId(Integer ownerId);

  // Custom query to find pets by weight greater than a given value
  List<Pet> findByWeightGreaterThan(Double weight);

  // Custom JPQL query to find pets by breed
  @Query("SELECT p FROM Pet p WHERE p.breed = ?1")
  List<Pet> findByBreed(String breed);

  // Custom query to find pets by gender
  List<Pet> findByGender(Enums.Gender gender);
}