package com.example.server.auth.exception;

public class CustomException extends RuntimeException {
  public CustomException(String message, Exception exception) {
    super(message, exception);
  }

  public CustomException(String message) {
    super(message);
  }
}