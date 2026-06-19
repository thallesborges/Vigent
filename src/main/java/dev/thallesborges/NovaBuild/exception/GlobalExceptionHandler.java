package dev.thallesborges.NovaBuild.exception;

import dev.thallesborges.NovaBuild.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildError(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(
                new ErrorResponse(
                        status.value(),
                        message,
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEmailExiste(EmailExisteException ex) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNomeProdutoExiste(NomePlanoExisteException ex) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleCnpjInvalido(CnpjInvalidoException ex) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRazaoSocialExiste(RazaoSocialExisteException ex) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleTelefoneExiste(TelefoneExisteException ex) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlePlanoNotFound(PlanoNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
