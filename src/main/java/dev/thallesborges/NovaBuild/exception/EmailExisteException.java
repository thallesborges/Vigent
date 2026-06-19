package dev.thallesborges.NovaBuild.exception;

public class EmailExisteException extends RuntimeException {
    public EmailExisteException(String message) {
        super(message);
    }
}
