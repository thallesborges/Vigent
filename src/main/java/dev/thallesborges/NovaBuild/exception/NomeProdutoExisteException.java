package dev.thallesborges.NovaBuild.exception;

public class NomeProdutoExisteException extends RuntimeException {
    public NomeProdutoExisteException(String message) {
        super(message);
    }
}
