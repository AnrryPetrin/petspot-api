package com.anrry.petspot.global.exceptions;

public class InvalidRequestException extends RuntimeException {
  
      public InvalidRequestException(String errorMessage) {
          super(errorMessage);
      }
 
}