package com.anrry.petspot.modules.owner;

import java.util.ArrayList;
import java.util.List;

import com.anrry.petspot.modules.pet.Pet;
import com.anrry.petspot.modules.user.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "owners")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "pets", callSuper = true)
@ToString(exclude = "pets", callSuper = true)
public class Owner extends User {

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Pet> pets = new ArrayList<>();

  // Helper methods to manage the bidirectional relationship with Pet
  public void addPet(Pet pet) {
    pets.add(pet);
    pet.setOwner(this);
  }

  public void removePet(Pet pet) {
    pets.remove(pet);
    pet.setOwner(null);
  }
}
