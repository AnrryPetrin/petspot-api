package com.anrry.petspot.modules.vaccine;

import java.util.ArrayList;
import java.util.List;

import com.anrry.petspot.modules.pet.Enums;
import com.anrry.petspot.modules.pet.Pet;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vaccines")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Enums.Species species;

    @ManyToMany(mappedBy = "vaccines")
    @Builder.Default
    @JsonIgnore
    private List<Pet> pets = new ArrayList<>();

}
