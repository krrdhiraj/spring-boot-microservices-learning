package com.eLearn.app.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomMessage {

    private String message;

    private boolean success;

    private HttpStatus status;
}
