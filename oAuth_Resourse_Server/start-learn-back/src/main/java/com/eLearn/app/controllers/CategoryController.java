package com.eLearn.app.controllers;

import com.eLearn.app.config.AppConstants;
import com.eLearn.app.dtos.CategoryDto;
import com.eLearn.app.dtos.CourseDto;
import com.eLearn.app.dtos.CustomMessage;
import com.eLearn.app.dtos.CustomPageResponse;
import com.eLearn.app.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
//@CrossOrigin(origins = "*") --> for all origins
//@CrossOrigin(origins ="http://localhost:5173", originPatterns = {"http://localhost:4200","http://localhost:5073"})
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody CategoryDto categoryDto
            // BindingResult -> is used for custom message of validation or error handling of validation
//            BindingResult result
    ){
//        if(result.hasErrors()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
//        }
        CategoryDto createdCategoryDto = categoryService.create(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoryDto);
    }

    @GetMapping("/all")
    public List<CategoryDto> getAll(){
        List<CategoryDto> all = categoryService.getAll();
        return all;
    }

    // this will sort and do pagination
    @GetMapping("/pages")
    public CustomPageResponse<CategoryDto> getAllPages(
            @RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy" , required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy
    ){
        return categoryService.getAllPages(pageNumber, pageSize, sortBy);
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getSingle(
            @PathVariable String categoryId
    ){
        return categoryService.get(categoryId);
    }

    @PutMapping("/{categoryId}")
    public CategoryDto update(
            @PathVariable String categoryId,
            @RequestBody CategoryDto categoryDto
    ){
        return categoryService.update(categoryDto,categoryId);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CustomMessage> delete(
            @PathVariable String categoryId
    ){
      categoryService.delete(categoryId);
      CustomMessage customMessage = new CustomMessage();
      customMessage.setMessage("Category deleted successfully!");
      customMessage.setSuccess(true);
      customMessage.setStatus(HttpStatus.OK);
      return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }

    // add course and category
    @PostMapping("/{categoryId}/courses/{courseId}")
    public ResponseEntity<CustomMessage> addCourseToCategory(
            @PathVariable String categoryId,
            @PathVariable String courseId
    ){
        categoryService.addCourseToCategory(categoryId,courseId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("Course and Category updated.");
        customMessage.setSuccess(true);
        customMessage.setStatus(HttpStatus.CREATED);
        return ResponseEntity.ok(customMessage);
    }

    //get all courses from category
    @GetMapping("/{categoryId}/courses")
    public ResponseEntity<List<CourseDto>> getCourseOfCategory(
            @PathVariable String categoryId
    ){
        return ResponseEntity.ok(categoryService.getCourseofCategory(categoryId));
    }
}
