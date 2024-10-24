package com.anrry.petspot.modules.owner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anrry.petspot.modules.owner.Owner;
import com.anrry.petspot.modules.owner.services.OwnerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

  @Autowired
  private OwnerService ownerService;

  @GetMapping
  public ResponseEntity<List<Owner>> getAllOwners() {
    List<Owner> owners = ownerService.getAllOwners();
    return new ResponseEntity<>(owners, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Owner> getOwnerById(@PathVariable Integer id) {
    Optional<Owner> owner = ownerService.getOwnerById(id);
    return owner.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
    Owner createdOwner = ownerService.createOwner(owner);
    return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Owner> updateOwner(@PathVariable Integer id, @RequestBody Owner ownerDetails) {
    try {
      Owner updatedOwner = ownerService.updateOwner(id, ownerDetails);
      return new ResponseEntity<>(updatedOwner, HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOwner(@PathVariable Integer id) {
    ownerService.deleteOwner(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
