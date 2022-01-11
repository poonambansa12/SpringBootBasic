package com.example.web.exception;

/**
 * This exception class is to handle the scenario
 * where Book object is not found for the given Id or title.
 */
public class BookNotFoundException extends RuntimeException {

  public BookNotFoundException() {
    super();
  }

  public BookNotFoundException(final String message) {
    super(message);
  }

  public BookNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public BookNotFoundException(final Throwable cause) {
    super(cause);
  }
}
