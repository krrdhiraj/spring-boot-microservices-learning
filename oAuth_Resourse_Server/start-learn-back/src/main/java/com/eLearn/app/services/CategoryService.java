package com.eLearn.app.services;

import com.eLearn.app.dtos.CategoryDto;
import com.eLearn.app.dtos.CourseDto;
import com.eLearn.app.dtos.CustomPageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    CategoryDto create(CategoryDto categoryDto);

    List<CategoryDto> getAll();

    CustomPageResponse<CategoryDto> getAllPages(int pageNumber, int pageSize, String sortBy);

    CategoryDto get(String categoryId);

    CategoryDto update(CategoryDto categoryDto, String categoryId);

    void delete(String categoryId);

    void addCourseToCategory(String categoryId, String courseId);

    List<CourseDto> getCourseofCategory(String categoryId);
}
