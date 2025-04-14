package com.spring.boot.aop.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

//@Component
public class Employee {
    private String salary;

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Employee(){
        this.salary = salary;
        System.out.println("Employee is created...");
    }

    public void work(){
        System.out.println("everyone is working" + " : , His salary is " + this.salary);
    }

//    @Override
//    @PostConstruct
    public void afterSetProperties() throws Exception {
        System.out.println("Initializing bean through xml configuration : Employee");
        System.out.println("DB connection is open");
    }

//    @Override
//    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("Cleaning up Bean via XML configuration: Employee");
        System.out.println("DB connection is closing...");
    }
}
