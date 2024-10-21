package com.anrry.petspot.model;

import java.time.LocalDate;
import java.util.UUID;

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
  private UUID id;

  @NotBlank(message = "Pet name is mandatory")
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @NotNull(message = "Species is mandatory")
  @Enumerated(EnumType.STRING)
  @Column(name = "species", nullable = false, length = 50)
  private Species species;

  @NotBlank(message = "Breed is mandatory")
  @Column(name = "breed", nullable = false, length = 100)
  private String breed;

  @Positive(message = "Weight must be positive")
  @Column(name = "weight", nullable = false, precision = 5, scale = 2)
  private Double weight; // in kilograms

  @PastOrPresent(message = "Date of birth cannot be in the future")
  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @NotNull(message = "Gender is mandatory")
  @Enumerated(EnumType.STRING)
  @Column(name = "gender", nullable = false, length = 20)
  private Gender gender;

  @NotNull(message = "Neutered status is mandatory")
  @Column(name = "is_neutered", nullable = false)
  private Boolean isNeutered;

  @Column(name = "color", length = 50)
  private String color;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "owner_id", nullable = false)
  private Owner owner;

  // Enumerations
  public enum Gender {
    MALE,
    FEMALE,
    OTHER
  }

  public enum Species {
    DOG,
    CAT,
    RABBIT,
    BIRD,
    OTHER
  }
}
