package com.elearn.app.exceptions;

import com.elearn.app.dtos.CustomMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomMessage> handleResourceNotFound(ResourceNotFoundException ex){
        CustomMessage customMessage = new CustomMessage();

        customMessage.setMessage(ex.getMessage());
        customMessage.setStatus(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customMessage);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationError(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<CustomMessage> handleAuthorizationDeniedException(AuthorizationDeniedException ex){
        CustomMessage message = new CustomMessage();
        message.setMessage(ex.getMessage());
        message.setStatus(false);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }
}
