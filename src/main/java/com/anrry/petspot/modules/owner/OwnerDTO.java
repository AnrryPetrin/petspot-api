package com.anrry.petspot.modules.owner;

import java.util.List;

import com.anrry.petspot.modules.pet.PetDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnerDTO {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private List<PetDTO> pets;
}
