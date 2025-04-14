package com.eLearn.app.servicesImpl;

import com.eLearn.app.Repositories.CategoryRepo;
import com.eLearn.app.Repositories.CourseRepo;
import com.eLearn.app.dtos.CategoryDto;
import com.eLearn.app.dtos.CourseDto;
import com.eLearn.app.dtos.CustomPageResponse;
import com.eLearn.app.entites.Category;
import com.eLearn.app.entites.Course;
import com.eLearn.app.exceptions.ResourceNotFoundException;
import com.eLearn.app.services.CategoryService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    ModelMapper modelMapper ;
    CategoryRepo categoryRepo;
    CourseRepo courseRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo, CourseRepo courseRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        String id = UUID.randomUUID().toString();
        categoryDto.setId(id);
        categoryDto.setAddedDate(new Date());
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCat = categoryRepo.save(category);
        CategoryDto mapped = modelMapper.map(savedCat, CategoryDto.class);
        return mapped;
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> allCategory = categoryRepo.findAll();
        return allCategory.stream().map(category -> modelMapper.map(category,CategoryDto.class)).toList();
    }
    @Override
    public CustomPageResponse<CategoryDto> getAllPages(int pageNumber, int pageSize, String sortBy) {

        // sorting
        Sort sort = Sort.by(sortBy).descending();

        // Page Request
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<Category> pager = categoryRepo.findAll(pageRequest);
        List<Category> allCategory = pager.getContent();

        List<CategoryDto> categoryDtoList = allCategory.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
        CustomPageResponse<CategoryDto> customPageResponse = new CustomPageResponse<>();

        customPageResponse.setContent(categoryDtoList);
        customPageResponse.setLast(pager.isLast());
        customPageResponse.setTotalElements((int) pager.getTotalElements());
        customPageResponse.setTotalPages(pager.getTotalPages());
        customPageResponse.setPageNumber(pageNumber);
        customPageResponse.setPageSize(pager.getSize());

        return customPageResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = categoryRepo.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category not found !!!"));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        Category category = categoryRepo.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category not found !!!"));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        categoryRepo.save(category);
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepo.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category not found !!!"));
        categoryRepo.delete(category);
    }

    @Override
    @Transactional
    public void addCourseToCategory(String categoryId, String courseId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("No category found!"));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("No course found!"));
        category.addCourse(course);
        categoryRepo.save(category);
        System.out.println("Course updated...!");
    }

    @Override
    @Transactional
    public List<CourseDto> getCourseofCategory(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("No category found!"));
        List<Course> courseList = category.getCourseList();
        return courseList.stream().map(course -> modelMapper.map(course, CourseDto.class)).toList();
    }
}
