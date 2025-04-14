package com.spring.core.spring_boot_core.Services;

import org.springframework.stereotype.Component;

@Component
//@Primary
public class MySqlLoginService implements LoginService{

    @Override
    public void loginUser() throws InterruptedException {
        System.out.println("Logged in using mySql DB");
        System.out.println("wait....");
        Thread.sleep(5000);
        System.out.println("working...");
        Thread.sleep(5000);
    }

    @Override
    public void logoutUser() {
        System.out.println("Logged out using mySql DB");
    }
}
