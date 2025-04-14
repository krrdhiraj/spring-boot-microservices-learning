package com.elearn.app.services;


import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomPageResponse;

import java.util.List;

public interface CategoryService {

    // insert/create data
    CategoryDto insert(CategoryDto categoryDto);

    // get Category by id or title
    CategoryDto get(String categoryId);

    // get all category
    List<CategoryDto> getAll();

    // delete category
     void delete(String categoryId);

    // update category
    CategoryDto update(String categoryId, CategoryDto categoryDto);

    CustomPageResponse<CategoryDto> getAllPages(int pageNumber, int pageSize, String sortBy, String sortDir);

    // search

    void addCourseToCategory(String courseId, String categoryId);

    List<CourseDto> getCoursesFromCategory(String categoryId);
}
