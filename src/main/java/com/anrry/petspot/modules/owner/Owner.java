package com.anrry.petspot.modules.owner;

import java.util.ArrayList;
import java.util.List;

import com.anrry.petspot.modules.pet.Pet;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "owners")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "pets", callSuper = true)
public class Owner {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotBlank(message = "Name is mandatory")
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Email(message = "Email should be valid")
  @NotBlank(message = "Email is mandatory")
  @Column(name = "email", nullable = false, unique = true, length = 100)
  private String email;

  @NotBlank(message = "Password is mandatory")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$", message = "Password must be 8-20 characters long, include uppercase and lowercase letters, a number, and a special character.")
  @Column(name = "password", nullable = false, length = 255)
  private String password;

  @Pattern(regexp = "^\\+?55\\s?\\(?[1-9]{2}\\)?\\s?[9]?[6-9]{1}[0-9]{3}\\-?[0-9]{4}$", message = "Phone number is invalid")
  @NotBlank(message = "Phone number is mandatory")
  @Column(name = "phone", nullable = false, unique = true, length = 20)
  private String phone;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
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
