package com.elearn.app.controllers;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomMessage;
import com.elearn.app.dtos.CustomPageResponse;
import com.elearn.app.dtos.ResourceContentType;
import com.elearn.app.entities.Course;
import com.elearn.app.services.CourseService;
import com.elearn.app.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.util.ResourceSet;
import org.aspectj.asm.IModelFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;


@SecurityRequirement(name = "jwtScheme") // for Authorization to access the apis
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FileService fileService;


    @Operation(summary = "Create Course",
            description = "Pass info to create new courses",
            tags = {"Operation-Post"}
    )
    @ApiResponse(responseCode = "201", description = "Course created successfully.")
    @ApiResponse(responseCode = "501", description =  "Internal Server error")
    @PostMapping
    public ResponseEntity<CourseDto> insert(
            @RequestBody CourseDto courseDto
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(courseDto));
    }
    @GetMapping("/{courseId}")
    public CourseDto getSingle(
            @PathVariable String courseId
    ){
        return courseService.getSingleCourse(courseId);
    }

    @GetMapping
    public List<Course> getAll(){
        return courseService.getAllCourse().stream().map(courseDto ->
                modelMapper.map(courseDto,Course.class)).toList();
    }

    @Operation(summary = "Update Course",
            description = "Pass info to update the courses"
    )
    @ApiResponse(responseCode = "201", description = "Course updated successfully.")
    @ApiResponse(responseCode = "401", description =  "Unauthorized, pls pass the correct credentials")
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(
            @Parameter(description =  "Course id , which one to update.")
            @PathVariable String courseId,
            @RequestBody CourseDto courseDto

    ){
        CourseDto update = courseService.update(courseDto, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(update);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(
            @PathVariable String courseId
    ){
        courseService.delete(courseId);
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully😊.");
    }

    @GetMapping("/pages")
    public CustomPageResponse<CourseDto> getAllpages(
            @RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SORT_BY) String sortBy
    ){
        return  courseService.getAllCoursesPages(pageNum,pageSize,sortBy);
    }
    @GetMapping("/search")
    public ResponseEntity<List<CourseDto>> searchCourses(String keyword){
        return ResponseEntity.ok(courseService.searchCourses(keyword));
    }
    @PostMapping("/{courseId}/banner")
    public ResponseEntity<?> uploadBanner(
            @PathVariable String courseId,
            @RequestParam("banner")MultipartFile banner
            ) throws IOException {
//        System.out.println(banner.getOriginalFilename());
//        System.out.println(banner.getName());
//        System.out.println(banner.getSize());
//        System.out.println(banner.getContentType());
//        fileService.save(banner,AppConstants.COURSE_BANNER_UPLOAD_DIR, banner.getOriginalFilename());
//        System.out.println(banner.get);

        String contentType = banner.getContentType();
        if(contentType == null){
            contentType = "image/png";
        }else{
            if(!contentType.equalsIgnoreCase("image/png") ||   !contentType.equalsIgnoreCase("image/jpg")){
                CustomMessage customMessage = new CustomMessage();
                customMessage.setStatus(false);
                customMessage.setMessage("Invalid file");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customMessage);
            }
        }

        CourseDto courseDto = courseService.saveBanner(banner, courseId);
        return ResponseEntity.ok(courseDto);
    }
    // serve banner
    @ResponseStatus(HttpStatus.CREATED) // to change the status
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/{courseId}/banners")
    public ResponseEntity<Resource> serveBanner(
            @PathVariable String courseId,
            @RequestHeader("Content-Type") String contentType,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpSession httpSession,
            ServletContext servletContext
    ){
        ResourceContentType resourceContentType = courseService.getCourseBannerId(courseId);
        System.out.println(httpServletRequest.getContextPath());

        // header Names
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            System.out.println( header+ " : " + httpServletRequest.getHeader(header) );
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(resourceContentType.getContentType()))
                .body(resourceContentType.getResource());
    }
}
