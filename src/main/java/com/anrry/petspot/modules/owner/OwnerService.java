package com.anrry.petspot.modules.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

  @Autowired
  private OwnerRepository ownerRepository;

  public List<Owner> getAllOwners() {
    return ownerRepository.findAll();
  }

  public Optional<Owner> getOwnerById(Integer id) {
    return ownerRepository.findById(id);
  }

  public Owner createOwner(Owner owner) {
    return ownerRepository.save(owner);
  }

  public Owner updateOwner(Integer id, Owner ownerDetails) {
    return ownerRepository.findById(id).map(owner -> {
      owner.setName(ownerDetails.getName());
      owner.setEmail(ownerDetails.getEmail());
      owner.setPhone(ownerDetails.getPhone());
      owner.setPassword(ownerDetails.getPassword());
      return ownerRepository.save(owner);
    }).orElseThrow(() -> new RuntimeException("Owner not found with id " + id));
  }

  public void deleteOwner(Integer id) {
    ownerRepository.deleteById(id);
  }
}