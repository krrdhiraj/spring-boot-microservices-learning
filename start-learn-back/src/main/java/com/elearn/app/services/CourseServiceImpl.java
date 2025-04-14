package com.elearn.app.services;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomPageResponse;
import com.elearn.app.dtos.ResourceContentType;
import com.elearn.app.entities.Category;
import com.elearn.app.entities.Course;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepo courseRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FileService fileService;
    // Constructor Injection
    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseDto create(CourseDto courseDto) {
        courseDto.setId(UUID.randomUUID().toString());
        courseDto.setCreatedDate(new Date());
        Course savedCourse = courseRepo.save(this.dtoToEntity(courseDto));
        return entityToDto(savedCourse);
    }

    @Override
    public List<CourseDto> getAllCourse() {
        List<Course> courseList = courseRepo.findAll();
        List<CourseDto> courseDtoList = courseList
                .stream()
                .map(course -> entityToDto(course)).collect(toList());
//                .collect(Collectors.toList());
        return courseDtoList;
    }

    @Override
    public CourseDto getSingleCourse(String courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException("No course found with this Id🫣, Pls try id"));
        return modelMapper.map(course, CourseDto.class);
    }

    @Override
    public CourseDto update(CourseDto courseDto, String courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException("No course found, Pls tell me which one to update"));
          modelMapper.map(courseDto,course);
//        course.setPrice(courseDto.getPrice());
//        course.setLive(courseDto.isLive());
//        course.setDiscount(course.getDiscount());
//        course.setTitle(courseDto.getTitle());
//        course.setBanner(courseDto.getBanner());
//        course.setBannerContentType(courseDto.getBannerContentType());
//        course.setLongDesc(courseDto.getLongDesc());
//        course.setShortDesc(courseDto.getShortDesc());
//        course.setVideoList(courseDto.getVideoDtoList()));
        Course savedCourse = courseRepo.save(course);
        return modelMapper.map(savedCourse,CourseDto.class);
    }

    @Override
    public ResponseEntity<?> delete(String courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(()
                -> new ResourceNotFoundException("Course not found to delete!"));
        courseRepo.delete(course);
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted Successfully.");
    }

    @Override
    public List<CourseDto> searchCourses(String keyword) {

//         if(title){
//
//         }else if(description){
//
//         }else if(price){
//
//         }
        List<Course> courseList = courseRepo.findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(keyword, keyword);
        return courseList.stream().map(course -> modelMapper.map(course,CourseDto.class)).toList();
    }

    @Override
    public CustomPageResponse<CourseDto> getAllCoursesPages(int pageNum, int pageSize, String sortBy) {

        PageRequest pageable = PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        Page<Course> coursePages = courseRepo.findAll(pageable);
        List<Course> courseList = coursePages.getContent();
        List<CourseDto> courseDtoList = courseList.stream().map(course ->
                modelMapper.map(course, CourseDto.class)).toList();

        CustomPageResponse<CourseDto> customPageResponse = new CustomPageResponse<>();

        customPageResponse.setTotalPages(coursePages.getTotalPages());
        customPageResponse.setLast(coursePages.isLast());
        customPageResponse.setContent(courseDtoList);
        customPageResponse.setPageNumber(pageNum);
        customPageResponse.setPageSize(coursePages.getSize());
        customPageResponse.setTotalElements(coursePages.getTotalElements());

        return customPageResponse;
    }

    @Override
    public CourseDto saveBanner(MultipartFile file, String courseId) throws IOException {

        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found!!"));
        String filePath = fileService.save(file, AppConstants.COURSE_BANNER_UPLOAD_DIR, file.getOriginalFilename());
        course.setBanner(filePath);
        course.setBannerContentType(file.getContentType());

        return modelMapper.map(courseRepo.save(course),CourseDto.class);
    }

    @Override
    public ResourceContentType getCourseBannerId(String courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        String bannerPath = course.getBanner();
        Path path = Paths.get(bannerPath);
        Resource resourse = new FileSystemResource(path);
        ResourceContentType resourceContentType = new ResourceContentType();
        resourceContentType.setResource(resourse);
        resourceContentType.setContentType(course.getBannerContentType());
        return resourceContentType;
    }

    // No need to use this model mapper automatically map this
    Course dtoToEntity(CourseDto courseDto){
//        Course course = new Course();
//        course.setId(courseDto.getId());
//        course.setTitle(courseDto.getTitle());
//        course.setPrice(courseDto.getPrice());
        // but all this is a mannual task so we'll use ModelMapper

        Course course = modelMapper.map(courseDto, Course.class);
        return course;
    }

    CourseDto entityToDto(Course course){
//        CourseDto courseDto = new CourseDto();
//        courseDto.setId(course.getId());
//        courseDto.setTitle(course.getTitle());

        return modelMapper.map(course, CourseDto.class);
    }

}
