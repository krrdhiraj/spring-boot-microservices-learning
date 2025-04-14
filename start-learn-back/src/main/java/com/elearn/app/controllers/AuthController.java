package com.elearn.app.controllers;

import com.elearn.app.config.securities.JwtUtil;
import com.elearn.app.dtos.CustomUserDetail;
import com.elearn.app.dtos.JwtResponse;
import com.elearn.app.dtos.LoginRequest;
import com.elearn.app.dtos.UserDto;
import com.elearn.app.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager manager;

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    private final ModelMapper modelMapper;

    public AuthController(JwtUtil jwtUtil, UserDetailsService userDetailsService, AuthenticationManager manager, ModelMapper modelMapper) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.manager = manager;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest){
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword());
            Authentication authenticated = manager.authenticate(authenticationToken);

        }catch (AuthenticationException ex){
            throw new BadCredentialsException("Invalid user Details.");
        }
//      authenticated
        CustomUserDetail userDetails = (CustomUserDetail)userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String token = jwtUtil.generateToken(userDetails.getUsername());

        User user = userDetails.getUser();

        JwtResponse build = JwtResponse.builder()
                .token(token)
                .user(modelMapper.map(user, UserDto.class))
                .build();

        return ResponseEntity.ok(build);
    }
}
