package com.mvc.example.controller;

import com.mvc.example.entities.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController = @Controller + @ResponseBody
@RestController
@RequestMapping("/home")
public class HomeController {

//    @GetMapping("/category")
    @RequestMapping("/category")
    public Category category(){
        Category category = new Category();
        category.setId(789908);
        category.setTitle("Desktop");
        category.setDescription("This is most trending category!");

        return category;
    }
}
