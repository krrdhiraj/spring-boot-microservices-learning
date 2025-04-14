package com.elearn.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
//@PreAuthorize("hasRole('ADMIN')")
public class TestController {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String test(){
        return "testing through create API's";
    }

    @GetMapping
    @PreAuthorize("hasRole('GUEST')")
    public String testing(){
        return "testing through get API's";
    }

    @GetMapping("/all")
    public String testingAll(){
        return "testing all apis";
    }
}
