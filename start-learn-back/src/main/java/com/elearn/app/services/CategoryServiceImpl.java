package com.elearn.app.services;

import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomPageResponse;
import com.elearn.app.entities.Category;
import com.elearn.app.entities.Course;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.CategoryRepo;
import com.elearn.app.repositories.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    private final CourseRepo courseRepo;
    public CategoryServiceImpl(CategoryRepo categoryRepo, CourseRepo courseRepo){

        this.categoryRepo = categoryRepo;
        this.courseRepo = courseRepo;
    }
    @Override
    public CategoryDto insert(CategoryDto categoryDto) {
        // create random category id
        String catId = UUID.randomUUID().toString();
        categoryDto.setId(catId);
        categoryDto.setAddedDate(new Date());
        // convert dto to Entity
        Category savedCategory = categoryRepo.save(modelMapper.map(categoryDto, Category.class));

        // return dto after converting entity to dto
        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->
                new ResourceNotFoundException("Category not found !"));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepo.findAll().stream().map(category ->
                modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(String categoryid) {
        Category category = categoryRepo.findById(categoryid).orElseThrow(() -> new ResourceNotFoundException("Category not found ! "));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto update(String categoryId, CategoryDto categoryDto) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found , Pls enter the valid category Id"));
        category.setTitle(categoryDto.getTitle());
        category.setDesc(categoryDto.getDesc());
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CustomPageResponse<CategoryDto> getAllPages(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sortedBy = Sort.by(sortBy);
        Sort.Direction direction = Sort.Direction.fromOptionalString(sortDir).orElse(Sort.Direction.ASC);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));
        Page<Category> categoryPages = categoryRepo.findAll(pageRequest);
        List<Category> categoryList = categoryPages.getContent();
        List<CategoryDto> categoryDtoList = categoryList.stream().map(category ->
                modelMapper.map(category, CategoryDto.class)).toList();

        CustomPageResponse<CategoryDto> customPageResponse = new CustomPageResponse<>();

        customPageResponse.setTotalPages(categoryPages.getTotalPages());
        customPageResponse.setLast(categoryPages.isLast());
        customPageResponse.setContent(categoryDtoList);
        customPageResponse.setPageNumber(pageNumber);
        customPageResponse.setPageSize(categoryPages.getSize());
        customPageResponse.setTotalElements(categoryPages.getTotalElements());

        return customPageResponse;
    }

    @Override
    public void addCourseToCategory(String categoryId, String courseId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found!!"));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found!!"));

        // category k andar course ko add kiye
        category.addCourse(course);

        categoryRepo.save(category);

        System.out.println("Category Course relationship updated!!!");
    }

    @Override
    public List<CourseDto> getCoursesFromCategory(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category not found!!"));
        List<Course> courseList = category.getCourseList();
        return courseList.stream().map(course -> modelMapper.map(course, CourseDto.class)).toList();
    }
}
