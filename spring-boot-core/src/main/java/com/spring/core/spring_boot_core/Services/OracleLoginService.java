package com.spring.core.spring_boot_core.Services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary --> it will make it primary and call first
public class OracleLoginService implements LoginService{
    @Override
    public void loginUser() throws InterruptedException {
        System.out.println("logged in using oracle DB");
    }

    @Override
    public void logoutUser() {
        System.out.println("logged out using Oracle DB");
    }
}
