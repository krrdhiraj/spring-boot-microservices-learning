package com.eLearn.app.config;

import com.eLearn.app.dtos.CustomMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

// this class is for handling the exception in spring security not controller
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        // this is used in servlet for web pages, but here we will send json data
//        response.setContentType("text/html");

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // we have to create json and write json
        CustomMessage customMessage = new CustomMessage();
        customMessage.setStatus(HttpStatus.UNAUTHORIZED);
        customMessage.setSuccess(false);
//        customMessage.setMessage("Unauthorized! : "+ authException.getMessage());

        if(authException instanceof BadCredentialsException){
            customMessage.setMessage("Invalid username or password!!!");
        }else{
            customMessage.setMessage("Wrong credentials : "+ authException.getMessage());
        }


        //now manually write json to response object
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(customMessage);


        // to send the data
        PrintWriter writer = response.getWriter();
//        writer.println("Invalid details, Please cross check your credentials!!" + authException.getMessage());
        writer.println(jsonString);

//        authException.printStackTrace();
    }
}
