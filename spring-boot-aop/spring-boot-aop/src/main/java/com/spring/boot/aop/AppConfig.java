package com.spring.boot.aop;

import com.spring.boot.aop.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//@Configuration
//@ImportResource("classpath:config.xml")
public class AppConfig {
    @Bean
    public Employee employee3(){

        Employee emp = new Employee();
        emp.setSalary("50 thousand");
        System.out.println("employee3 is created with salary : " + emp.getSalary());
        return emp;
    }
}
