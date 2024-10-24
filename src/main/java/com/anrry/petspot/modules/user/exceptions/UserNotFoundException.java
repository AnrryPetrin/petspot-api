package com.anrry.petspot.modules.user.exceptions;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String errorMessage) {
    super(errorMessage);
  }

}
