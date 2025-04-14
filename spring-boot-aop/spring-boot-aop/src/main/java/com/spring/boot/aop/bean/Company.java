package com.spring.boot.aop.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("employee4")
public class Company {
    private Employee employee;

    public  Company(@Qualifier("employee3") Employee employee){
        this.employee = employee;
//        employee.setSalary("24k");
        System.out.println("Creating Company object.");
    }
    public void work(){
        System.out.println("Employee is working.");
        employee.work();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
