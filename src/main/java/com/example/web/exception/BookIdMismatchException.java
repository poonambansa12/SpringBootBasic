package com.example.web.exception;

/**
 * This exception class is to handle the scenario
 * where Book object is not found for the given Id or title.
 */
public class BookIdMismatchException extends RuntimeException {

  public BookIdMismatchException() {
    super();
  }

  public BookIdMismatchException(final String message) {
    super(message);
  }

  public BookIdMismatchException(final String message, Throwable cause) {
    super(message, cause);
  }

  public BookIdMismatchException(Throwable cause) {
    super(cause);
  }
}
