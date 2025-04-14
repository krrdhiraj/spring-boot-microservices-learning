package com.mvc.example.controller;

import com.mvc.example.exception.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/about")
    public String about(Model model){
        System.out.println("This is about page.");
        model.addAttribute("name","Learn Spring MVC App with Dhiraj");
        return "about";
    }
    @RequestMapping("/home")
    @ResponseBody
    public List<String> home(){
        if(true){
            throw new ResourceNotFoundException("home page resource not found exception");
        }
        List<String> names = List.of("ankit","Krishna","Dhiraj","Chickoo");
        return names;
    }

    @GetMapping("/courses")
    @ResponseBody
    public Map<String,String> courses(){
        Map<String , String> courses= new HashMap<>();
        courses.put("Spring Boot", "8k");
        courses.put("React Js", "18k");
        courses.put("Android Dev", "12k");
        return  courses;
    }
}
