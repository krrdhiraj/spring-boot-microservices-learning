package com.eLearn.app.services;

import com.eLearn.app.dtos.CourseDto;
import com.eLearn.app.dtos.CustomPageResponse;
import com.eLearn.app.dtos.ResourceContentType;
import org.hibernate.boot.registry.selector.StrategyRegistration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CourseService {

    CourseDto create(CourseDto courseDto);

    List<CourseDto> getAll();

    CourseDto getSingleCourse(String courseId);

    CourseDto update(CourseDto courseDto, String courseId);

    void delete(String courseId);

    List<CourseDto> searchByTitle(String title);

    // Custom page k liye method 1
    CustomPageResponse<?> getAllPages(int pageSize, int pageNumber, String sortBy);

    // Method 2 for custome page response
    Page<CourseDto> getAllCourses(Pageable pageable);

    List<CourseDto> searchCourse(String keyword);

    CourseDto getCourseById(String id);

    CourseDto saveBanner(MultipartFile file, String courseId) throws IOException;

    ResourceContentType getCourseBannerById(String courseId);

}
