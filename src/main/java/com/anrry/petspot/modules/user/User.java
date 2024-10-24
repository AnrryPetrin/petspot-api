package com.anrry.petspot.modules.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass // This makes it a base class for entities, not a standalone entity.
public class User {

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

  @Pattern(regexp = "^\\+?55\\s?\\(?[1-9]{2}\\)?\\s?[9]?[6-9]{1}[0-9]{3}\\-?[0-9]{4}$", message = "Phone number is invalid")
  @NotBlank(message = "Phone number is mandatory")
  @Column(name = "phone", nullable = false, unique = true, length = 20)
  private String phone;
}
