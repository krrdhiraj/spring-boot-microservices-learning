package com.mvc.example.exception;

import com.mvc.example.model.ErrorRespons;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

//@ControllerAdvice + @ResponseBody = @RestControllerAdvice
@RestControllerAdvice
public class GlobalException {

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> exceptionHandelerCustomize(RuntimeException ex){
//        System.out.println(ex.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error occured : "+ ex.getMessage());
//    }

    // we can use our own exception
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> exceptionHandelerCustomize(ResourceNotFoundException ex){
//        System.out.println(ex.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error occured : "+ ex.getMessage());
//    }

    // we can send our own Response error message
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorRespons> exceptionHandler(ResourceNotFoundException ex){
       System.out.println(ex.getMessage());
        ErrorRespons errorRespons = new ErrorRespons();
        errorRespons.setMessage("Error occured "+ ex.getMessage());
        errorRespons.setStatus(HttpStatus.NOT_FOUND);
        errorRespons.setSuccess(false);
        errorRespons.setDate(new Date());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRespons);
    }
}
