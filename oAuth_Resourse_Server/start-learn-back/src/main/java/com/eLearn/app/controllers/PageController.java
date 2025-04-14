package com.eLearn.app.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController {

    @RequestMapping("/client-login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }
    @RequestMapping("/logout")
    public String logout(){
        return "logout";
    }
}
