package com.anrry.petspot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "owners", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email"),
    @UniqueConstraint(columnNames = "phone")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "pets")
@EqualsAndHashCode(exclude = "pets")
public class Owner {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank(message = "Owner name is mandatory")
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Email(message = "Email should be valid")
  @NotBlank(message = "Email is mandatory")
  @Column(name = "email", nullable = false, unique = true, length = 100)
  private String email;

  @Pattern(regexp = "^\\+?55\\s?\\(?[1-9]{2}\\)?\\s?[9]?[6-9]{1}[0-9]{3}\\-?[0-9]{4}$", message = "Phone number is invalid")
  @NotBlank(message = "Phone number is mandatory")
  @Column(name = "phone", nullable = false, unique = true, length = 20)
  private String phone;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Pet> pets = new ArrayList<>();

}
