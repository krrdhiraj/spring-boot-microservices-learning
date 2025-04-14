package com.spring.core.spring_boot_core.Services;

import org.springframework.stereotype.Component;

@Component
public interface LoginService {

    public void loginUser() throws InterruptedException;
    public void logoutUser();
}
