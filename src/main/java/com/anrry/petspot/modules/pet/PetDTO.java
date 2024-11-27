package com.anrry.petspot.modules.pet;

import java.time.LocalDate;
import java.util.List;

import com.anrry.petspot.modules.vaccine.VaccineDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDTO {
  private Integer id;
  private String name;
  private Enums.Species species;
  private String breed;
  private Double weight;
  private LocalDate dateOfBirth;
  private Enums.Gender gender;
  private Boolean isNeutered;
  private String color;
  private List<VaccineDTO> vaccines;
  private Integer ownerId;
}
