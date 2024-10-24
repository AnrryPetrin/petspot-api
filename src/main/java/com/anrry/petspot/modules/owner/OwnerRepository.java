package com.anrry.petspot.modules.owner;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

  // Custom query methods
  List<Owner> findByNameContainingIgnoreCase(String name);

  Optional<Owner> findByEmail(String email);

  List<Owner> findByPhoneContaining(String phone);

  List<Owner> findByPets_NameContainingIgnoreCase(String petName);

  boolean existsByEmail(String email);

  boolean existsByPhone(String phone);

}
