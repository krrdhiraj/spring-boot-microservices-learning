package com.elearn.app.controllers;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomMessage;
import com.elearn.app.dtos.CustomPageResponse;
import com.elearn.app.entities.Category;
import com.elearn.app.services.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
//@CrossOrigin(origins = "http://localhost:5173") // or "*" for passing any req
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper){
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }
    // get all category
    @GetMapping
    public List<Category> getAllCategory(){
        List<CategoryDto> categoryDtoList = categoryService.getAll();
        return categoryDtoList.stream().map(categoryDto ->
                modelMapper.map(categoryDto, Category.class)).toList();
    }
    // get PageNum & pageSize of Category
    @GetMapping("/pages")
    public CustomPageResponse<CategoryDto> getAllPages(
            @RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", required = false,defaultValue = AppConstants.DEFAULT_PAGE_SORT_BY) String sortBy,
            @RequestParam(value = "sortDir", required = false,defaultValue = AppConstants.DEFAULT_PAGE_SORT_DIR) String sortDir
    ){
        return categoryService.getAllPages(pageNumber, pageSize, sortBy, sortDir);
    }

    // create Category
    @PostMapping
//  public CategoryDto create( @RequestBody CategoryDto categoryDto){}
    public ResponseEntity<?> create(
          @Valid @RequestBody CategoryDto categoryDto
//          BindingResult result  --> it will accept all errors
    ){
//        if(result.hasErrors()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Data");
//        }
        CategoryDto createdCatDto = categoryService.insert(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCatDto);
    }
    // get single category
    @GetMapping("/{categoryId}")
    public CategoryDto getSingleCategory(
        @PathVariable String categoryId){
        return categoryService.get(categoryId);
    }

    // delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CustomMessage> delete(
            @PathVariable String categoryId
    ){
        categoryService.delete(categoryId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("Category Deleted !");
        customMessage.setStatus(true);
        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }
    // update Category
    @PutMapping("/{categoryId}")
    public CategoryDto update(
            @PathVariable String categoryId,
            @RequestBody CategoryDto categoryDto
    ){
        return categoryService.update(categoryId, categoryDto);
    }
    @PostMapping("/{categoryId}/courses/{courseId}")
    public ResponseEntity<?> addCourseToCategory(
            @PathVariable String categoryId,
            @PathVariable String courseId
    ){
        categoryService.addCourseToCategory(categoryId, courseId);
        return ResponseEntity.ok("Courses added to the Category !!");
    }
    @GetMapping("/{categoryId}/courses")
    public ResponseEntity<List<CourseDto>> getCourseOfCategory(
            @PathVariable String categoryId
    ){
        return ResponseEntity.ok(categoryService.getCoursesFromCategory(categoryId));
    }
}
