package com.eLearn.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
//@PreAuthorize("hasRole('ADMIN')")
public class TestController {

    // method level security we can do here
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String testing(){
        return "testing";
    }

    @GetMapping
    @PreAuthorize("hasRole('GUEST')")
    public String testing1(){
        return "testing1";
    }

    @GetMapping("/all")
    public String all(){
        // System.out.println(context.getDisplayName());
        return "api open endpoints";
    }

    @GetMapping("/auth")
    public String authenticted(){
        return "This is only auth end-points, no any role is required";
    }
}
