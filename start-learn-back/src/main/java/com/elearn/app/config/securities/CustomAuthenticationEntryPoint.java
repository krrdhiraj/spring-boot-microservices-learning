package com.elearn.app.config.securities;

import com.elearn.app.dtos.CustomMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // We have to create JSON and write jSON
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("Unauthorized!! " + authException.getMessage());
        customMessage.setStatus(false);

        // we can check which exception is generating
//        if(authException instanceof BadCredentialsException){
//            customMessage.setMessage("Invalid Username or Password !");
//        }else{
//            customMessage.setMessage("Unauthorized!!, Pls check your credentials and try again.");
//        }

        // Manually writing json to the response object
        ObjectMapper objectMapper = new ObjectMapper();
        String resString = objectMapper.writeValueAsString(customMessage);


        PrintWriter writer = response.getWriter();
        writer.println(resString);

//        authException.printStackTrace(); // to see in logs which exception is thrown

    }
}
