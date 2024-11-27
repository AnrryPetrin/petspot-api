package com.anrry.petspot.modules.pet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.anrry.petspot.modules.owner.Owner;
import com.anrry.petspot.modules.vaccine.Vaccine;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "owner")
@EqualsAndHashCode(exclude = "owner")
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotBlank(message = "Pet name is mandatory")
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @NotNull(message = "Species is mandatory")
  @Enumerated(EnumType.STRING)
  @Column(name = "species", nullable = false, length = 50)
  private Enums.Species species;

  @NotBlank(message = "Breed is mandatory")
  @Column(name = "breed", nullable = false, length = 100)
  private String breed;

  @Positive(message = "Weight must be positive")
  @Column(name = "weight", nullable = false)
  private Double weight; // in kilograms

  @PastOrPresent(message = "Date of birth cannot be in the future")
  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @NotNull(message = "Gender is mandatory")
  @Enumerated(EnumType.STRING)
  @Column(name = "gender", nullable = false, length = 20)
  private Enums.Gender gender;

  @NotNull(message = "Neutered status is mandatory")
  @Column(name = "is_neutered", nullable = false)
  private Boolean isNeutered;

  @Column(name = "color", length = 50)
  private String color;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "owner_id", nullable = false)
  @JsonBackReference
  private Owner owner;

  @ManyToMany
  @JoinTable(name = "pet_vaccine", joinColumns = @JoinColumn(name = "pet_id"), inverseJoinColumns = @JoinColumn(name = "vaccine_id"))
  @Builder.Default
  private List<Vaccine> vaccines = new ArrayList<>();

  // Converters
  public PetDTO toDTO() {
    return PetDTO.builder()
        .id(this.id)
        .name(this.name)
        .species(this.species)
        .breed(this.breed)
        .weight(this.weight)
        .dateOfBirth(this.dateOfBirth)
        .gender(this.gender)
        .isNeutered(this.isNeutered)
        .color(this.color)
        .build();
  }

  public static Pet fromDTO(PetDTO dto) {
    return Pet.builder()
        .id(dto.getId())
        .name(dto.getName())
        .species(dto.getSpecies())
        .breed(dto.getBreed())
        .weight(dto.getWeight())
        .dateOfBirth(dto.getDateOfBirth())
        .gender(dto.getGender())
        .isNeutered(dto.getIsNeutered())
        .color(dto.getColor())
        .build();
  }
}
