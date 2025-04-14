package com.eLearn.app.controllers;

import com.eLearn.app.Repositories.CourseRepo;
import com.eLearn.app.config.AppConstants;
import com.eLearn.app.dtos.*;
import com.eLearn.app.entites.Course;
import com.eLearn.app.services.CourseService;
import com.eLearn.app.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
//@SecurityRequirement(name="jwtScheme")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;

    @Operation(
        summary = "To create new Course",
            description = "Pass new course information to create new course"
//            tags = {"Course-controller"}
    )
    @ApiResponse(responseCode = "201", description = "Course created success")
    @ApiResponse(responseCode = "501", description = "Internal server error, Course not created")
    @PostMapping
    public ResponseEntity<CourseDto> insertCourse(
            @RequestBody CourseDto courseDto
    ){
        CourseDto insertCourse = courseService.create(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertCourse);
    }

    @GetMapping
    public List<?> getAllCourse(){
        List<CourseDto> allCourse = courseService.getAll();
        return allCourse;
    }
//    @GetMapping("/{courseId}")
//    public CourseDto getSingleCourse(
//            @PathVariable String courseId
//    ){
//        return courseService.getSingleCourse(courseId);
//    }

    @GetMapping("/pages")
    public CustomPageResponse<?> getAllPages(
            @RequestParam(name = "pageNumber", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy
    ){
        CustomPageResponse<CourseDto> allPages = (CustomPageResponse<CourseDto>) courseService.getAllPages(pageNumber, pageSize, sortBy);
        return allPages;
    }

    @Operation(summary = "Update the course",
    description = "Pass updated course info to update the course"
    )
    @PutMapping("/{courseId}")
    public CourseDto updateCourse(
            @Parameter(description = "Course id which is to update")
            @PathVariable String courseId,
            @RequestBody CourseDto courseDto
    ){
        CourseDto courseDt = courseService.getSingleCourse(courseId);
        courseDt.setTitle(courseDt.getTitle());
        courseDt.setLive(courseDto.isLive());
        courseDt.setDiscount(courseDto.getDiscount());
        courseDt.setLongDesc(courseDto.getLongDesc());
        courseDt.setShortDesc(courseDto.getShortDesc());
        courseDt.setVideos(courseDto.getVideos());
        courseDt.setBanner(courseDto.getBanner());
        courseDt.setCategoryList(courseDto.getCategoryList());
        courseDt.setPrice(courseDto.getPrice());
//        courseDt.setCreatedDate(new Date())
        return  courseService.create(courseDt);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(
            @PathVariable String courseId
    ){
        courseService.delete(courseId);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable String id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/allCourse")
    public ResponseEntity<Page<CourseDto>> getAllCourses(Pageable pageable) {
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse1(@PathVariable String id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseDto>> searchCourses(
            @RequestParam String keyword) {
        System.out.println("searching element");
        return ResponseEntity.ok(courseService.searchCourse(keyword));
    }

    @PostMapping("/{courseId}/banner")
    public ResponseEntity<?> bannerUploader(
            @PathVariable String courseId,
            @RequestParam("banner")MultipartFile file
            ) throws IOException {

        String contentType = file.getContentType();
        if(contentType == null){
            contentType = "image/png";
        }else{
            if(!(contentType.equalsIgnoreCase("image/png") || contentType.equalsIgnoreCase("image/jpeg"))){
                CustomMessage customMessage = new CustomMessage();
                customMessage.setMessage("Invalid file....");
                customMessage.setSuccess(false);
                customMessage.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok().body(customMessage);
            }
        }
        System.out.println(file.getOriginalFilename());
         System.out.println(file.getName());
         System.out.println(file.getSize());
         System.out.println(file.getResource());

//         fileService.saveFile(file, AppConstants.COURSE_BANNER_UPLOAD_DIR, file.getOriginalFilename());
        CourseDto courseDto = courseService.saveBanner(file, courseId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setStatus(HttpStatus.ACCEPTED);
        customMessage.setSuccess(true);
        customMessage.setMessage("Banner uploaded successfully!");
        return ResponseEntity.ok(courseDto);
    }

    // serve the banner
    @GetMapping("/{courseId}/banner")
    public ResponseEntity<?> getCourseBannerById(
            @PathVariable String courseId,
            @RequestHeader("Content-Type") String contentType,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            ServletContext context
    ){

//        System.out.println(request.getContextPath());
//        System.out.println(request.getPathInfo());
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while(headerNames.hasMoreElements()){
//            String header = headerNames.nextElement();
//            System.out.println(header + " " + request.getHeader(header));
//        }
        ResourceContentType resourceContentType = courseService.getCourseBannerById(courseId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(resourceContentType.getContentType())).body(resourceContentType.getResource());
    }
}
