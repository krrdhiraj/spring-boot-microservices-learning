package com.spring.boot.aop.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogsAspect {

    // advice -> actual code
//    @Before("execution(* com.spring.boot.aop.services.LoginService.doLogin()")
//    @Before("execution(* com.spring.boot.aop.services.LoginService.*())")
    @Before("execution(* com.spring.boot.aop.services.*.*())")
    public void maintainLogs(){
        System.out.println("Maintain executed logs : Before");
    }
    @After("execution(* com.spring.boot.aop.services.*.*(*))")
    public void maintainLogsAfter(){
        System.out.println("Maintain executed logs : After");
    }

}
