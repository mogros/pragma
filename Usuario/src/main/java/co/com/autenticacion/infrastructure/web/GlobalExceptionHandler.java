package co.com.autenticacion.infrastructure.web;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.time.OffsetDateTime;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ApiError> handleValidation(WebExchangeBindException ex) {
        List<String> details = ex.getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .toList();
        ApiError err = new ApiError(OffsetDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "Error de validación", details);
        log.warn("Validación fallida: {}", details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError> handleConflict(IllegalStateException ex) {
        ApiError err = new ApiError(OffsetDateTime.now(), HttpStatus.CONFLICT.value(),
                ex.getMessage(), List.of());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        log.error("Error inesperado", ex);
        ApiError err = new ApiError(OffsetDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ha ocurrido un error inesperado, por favor intente más tarde.", List.of());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @Value
    static class ApiError {
        OffsetDateTime timestamp;
        int status;
        String message;
        List<String> errors;
    }
}
