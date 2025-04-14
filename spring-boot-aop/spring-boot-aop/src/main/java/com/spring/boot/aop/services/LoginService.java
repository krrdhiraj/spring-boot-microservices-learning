package com.spring.boot.aop.services;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class LoginService {

    public void doLogin( ){
        System.out.println("Login user");
        System.out.println("login success");
    }

    public void logout(){
        System.out.println("Logout user successfully.");
    }
    public void mainLogic(String username, String password) {

        System.out.println("username : " + username);
        System.out.println("password : " + password);
        System.out.println("Access  the useful api's");

    }

    public void getAllUser(String username, String password) {
        System.out.println("printing users");
        System.out.println("user1");
        System.out.println("user2");
    }
}
