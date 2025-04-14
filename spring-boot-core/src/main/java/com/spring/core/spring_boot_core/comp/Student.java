package com.spring.core.spring_boot_core.comp;

import org.springframework.stereotype.Component;

@Component("student45")
public class Student {

  private String   name = "default";
  public Student(){
    System.out.println("Creating student object : ");
  }

  public void study(){
    System.out.println("Students are studying... " + name);
  }
  public String getName(){
    return this.name;
  }
  public void setName(String name){
    this.name = name;
  }

}
