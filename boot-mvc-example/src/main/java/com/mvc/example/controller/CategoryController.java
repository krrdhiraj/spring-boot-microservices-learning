package com.mvc.example.controller;

import com.mvc.example.entities.Category;
import com.mvc.example.model.CategoryCreateRequest;
import com.mvc.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // get All category
    @RequestMapping("/allCategory")
    private List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @RequestMapping("/singleCategory")
    private Category getCategory(){
        return categoryService.findById(2);
    }
    // get data by url path parameter
    @RequestMapping("{categoryId}")
    public Category getSingle(@PathVariable("categoryId") int id
//                              @PathVariable("title") String catTitle
    ){
//        System.out.println(catTitle);
        System.out.println(id);
        Category category = categoryService.findById(id);

        return category;
    }
    @RequestMapping("{categoryId}/{title}")
    public Category getSingleCategory(@PathVariable("categoryId") int id, @PathVariable("title") String catTitle){
        System.out.println(catTitle);
        System.out.println(id);
        return null;
    }
    // query parameter
    @RequestMapping("/query")
    public List<Category> getCategoryList(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNumber
    ){
        System.out.println("Page Number : " + pageNumber);
        System.out.println("Page Size : " + pageSize);
        return null;
    }

    // get Category and create category
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PostMapping
    public Category createCategory(
            @RequestBody CategoryCreateRequest categoryCreateRequest
    ){
        System.out.println(categoryCreateRequest);
        Category category = new Category();
        category.setTitle(categoryCreateRequest.getTitle());
        category.setDescription(categoryCreateRequest.getDescription());

        return categoryService.create(category);
    }

    // this is for handeling image
    @PostMapping("/image")
    public String imageUploader(
            @RequestParam ("file")MultipartFile file
    ){
        System.out.println(file.getOriginalFilename());
        return "image uploaded successfully";
    }
//  exception handling in mvc with simple message
//    @ExceptionHandler(RuntimeException.class)
    public  String exceptionHandler(RuntimeException ex){
        System.out.println(ex.getMessage());
        return "error occured : " + ex.getMessage();
    }
    // Exception handling in mvc with our own customize status code
//    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> exceptionHandelerCustomize(RuntimeException ex){
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error occured : "+ ex.getMessage());
    }
}
