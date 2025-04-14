package com.eLearn.app.exceptions;

import com.eLearn.app.dtos.CustomMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomMessage> exceptionHandler(ResourceNotFoundException ex){
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("Error occured : " + ex.getMessage());
        customMessage.setSuccess(false);
        customMessage.setStatus(HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customMessage);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();

      ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
//    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAuthorizationDeniedException(AccessDeniedException ex){
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage(ex.getMessage());
        customMessage.setSuccess(false);
        customMessage.setStatus(HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok(customMessage);
    }
//    @ExceptionHandler(AuthorizationDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleAccessDeniedException(AuthorizationDeniedException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp",LocalDateTime.now());
        body.put("status", HttpStatus.FORBIDDEN.value());
        body.put("error", "Forbidden");
        body.put("message", ex.getMessage());
        body.put("path", "/api/v1/categories");

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }
}
