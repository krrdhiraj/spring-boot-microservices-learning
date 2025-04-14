package com.elearn.app.services;

import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomMessage;
import com.elearn.app.dtos.CustomPageResponse;
import com.elearn.app.dtos.ResourceContentType;
import com.elearn.app.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface CourseService {

    CourseDto create(CourseDto courseDto);

    List<CourseDto> getAllCourse();

    CourseDto getSingleCourse(String courseId);

    CourseDto update(CourseDto courseDto, String courseId);

    ResponseEntity<?> delete(String courseId);

    List<CourseDto> searchCourses(String title);

    CustomPageResponse<CourseDto> getAllCoursesPages(int pageNum, int pageSize, String sortBy);

    CourseDto saveBanner(MultipartFile file, String courseId) throws IOException;

    ResourceContentType getCourseBannerId(String courseId);
}
