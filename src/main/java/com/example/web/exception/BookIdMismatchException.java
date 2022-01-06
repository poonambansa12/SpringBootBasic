package com.example.web.exception;

public class BookIdMismatchException extends RuntimeException {

    public BookIdMismatchException() {
        super();
    }

    public BookIdMismatchException(final String message) {
        super(message);
    }

    public BookIdMismatchException (final String message, Throwable cause) {
        super(message, cause);
    }

    public BookIdMismatchException (Throwable cause) {
        super(cause);
    }
}
