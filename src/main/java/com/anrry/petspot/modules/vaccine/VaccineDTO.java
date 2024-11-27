package com.anrry.petspot.modules.vaccine;

import com.anrry.petspot.modules.pet.Enums;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VaccineDTO {
    private Integer id;
    private String name;
    private String description;
    private Enums.Species species;
    private Integer petId;
}
