package com.spring.core.spring_boot_core.controller;

import com.spring.core.spring_boot_core.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HomeController {
    // if we apply @Autowired on properties , it will take only properties not constructor
//    @Autowired
//    @Qualifier("mySqlLoginService")
    private LoginService loginService;


    public HomeController(@Qualifier("mySqlLoginService") LoginService loginService){
        this.loginService = loginService;
    }

    public void loginUser() throws InterruptedException {
        loginService.loginUser();
        System.out.println("login success");
    }

    public void logoutUser(){
        loginService.logoutUser();
        System.out.println("logout success");
    }
}
