package com.spring.core.spring_boot_core.comp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Father {
//    @Autowired
//    @Qualifier("student4")
    private Student student;
    private Test test;

    // with the help of constructor
    private Father (@Qualifier("student4") Student student
            , Test test12){
        this.test = test12;
        this.student = student;
        System.out.println("Creating Father.");
    }
    public void useStudent(){
        System.out.println("using student");
        student.study();
    }
    public Student getStudent(){
        return student;
    }
    public void setTest(Test test) {
        this.test = test;
    }
    public Test getTest(){
        System.out.println("Getting teest");
        return test;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
}
