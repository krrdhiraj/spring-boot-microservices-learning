package com.eLearn.app.servicesImpl;

import com.eLearn.app.Repositories.CourseRepo;
import com.eLearn.app.config.AppConstants;
import com.eLearn.app.dtos.CategoryDto;
import com.eLearn.app.dtos.CourseDto;
import com.eLearn.app.dtos.CustomPageResponse;
import com.eLearn.app.dtos.ResourceContentType;
import com.eLearn.app.entites.Course;
import com.eLearn.app.exceptions.ResourceNotFoundException;
import com.eLearn.app.services.CourseService;
import com.eLearn.app.services.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FileService fileService;

    @Override
    public CourseDto create(CourseDto courseDto) {

        String id = UUID.randomUUID().toString();
        courseDto.setId(id);
        courseDto.setCreatedDate(new Date());
        Course course = this.dtoToEntity(courseDto);
        Course savedCourse = courseRepo.save(course);
        return entityToDto(savedCourse);
    }

    @Override
    public List<CourseDto> getAll() {
        List<Course> courseList = courseRepo.findAll();
        List<CourseDto> courseDtoList =
                courseList.stream()
                        .map(course -> entityToDto(course)).collect(toList());
        return courseDtoList;
    }

    @Override
    public CourseDto getSingleCourse(String courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Resourse not found!"));
        return modelMapper.map(course,CourseDto.class);
    }

    @Override
    public CourseDto update(CourseDto courseDto, String courseId) {
        Course course = courseRepo.findById(courseId).
                orElseThrow(() -> new ResourceNotFoundException("No course found with this id : " + courseId));
        modelMapper.map(courseDto,course);
        courseRepo.save(course);
        return entityToDto(course);
    }

    @Override
    public void delete(String courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found !!!"));
        courseRepo.delete(course);
    }

    @Override
    public List<CourseDto> searchByTitle(String title) {
        List<Course> byTitle = courseRepo.findByTitle(title);
        List<CourseDto> courseDtoList = byTitle.stream().map(course -> modelMapper.map(course, CourseDto.class)).collect(toList());
        return courseDtoList;
    }

    @Override
    public CustomPageResponse<?> getAllPages(int pageSize, int pageNumber, String sortBy) {
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<Course> pager = courseRepo.findAll(pageable);
        List<Course> allCourse = pager.getContent();
        List<CourseDto> courseDtoList = allCourse.stream().map(course -> modelMapper.map(course, CourseDto.class)).toList();
        CustomPageResponse<CourseDto> courseDtoCustomPageResponse = new CustomPageResponse<>();

        courseDtoCustomPageResponse.setTotalPages(pager.getTotalPages());
        courseDtoCustomPageResponse.setPageNumber(pageNumber);
        courseDtoCustomPageResponse.setPageSize(pageSize);
        courseDtoCustomPageResponse.setLast(pager.isLast());
        courseDtoCustomPageResponse.setContent(courseDtoList);
        return courseDtoCustomPageResponse;
    }

    @Override
    public Page<CourseDto> getAllCourses(Pageable pageable) {
        Page<Course> allCourses = courseRepo.findAll(pageable);
        List<Course> courses = allCourses.getContent();
        List<CourseDto> courseDtoList = courses.stream().map(course ->
                modelMapper.map(course, CourseDto.class)).toList();
        return new PageImpl<>(courseDtoList, pageable, allCourses.getTotalElements());
    }

    @Override
    public List<CourseDto> searchCourse(String keyword) {
//        courseRepo.findByTitle(keyword);
        List<Course> allCourse = courseRepo.findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(keyword,keyword);
        return allCourse.stream().map(course -> modelMapper.map(course, CourseDto.class)).toList();
    }

    @Override
    public CourseDto getCourseById(String id) {
        return modelMapper.map(courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No course found!")),CourseDto.class);
    }
    @Override
    public CourseDto saveBanner(MultipartFile file, String courseId) throws IOException {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("No course found!!!"));
        String filePath = fileService.saveFile(file, AppConstants.COURSE_BANNER_UPLOAD_DIR, file.getOriginalFilename());
        course.setBanner(filePath);
        course.setBannerContentType(file.getContentType());
        Course savedCourse = courseRepo.save(course);
        return modelMapper.map(savedCourse,CourseDto.class);
    }
    @Override
    public ResourceContentType getCourseBannerById(String courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found!!"));
        String bannerPath = course.getBanner();
        Path path = Paths.get(bannerPath);

        Resource resource=new FileSystemResource(path);

        ResourceContentType resourceContentType = new ResourceContentType();
        resourceContentType.setResource(resource);
        resourceContentType.setContentType(course.getBannerContentType());

        return resourceContentType;
    }
    public CourseDto entityToDto(Course course){
//        CourseDto courseDto = new CourseDto();
//        courseDto.setId(course.getId());
//        courseDto.setLive(course.isLive());
//        courseDto.setTitle(course.getTitle());

        // do for all fields
//   or directly we can do through Model mapper
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);

        return courseDto;
    }

    public Course dtoToEntity(CourseDto dto){
//        Course course = new Course();
//        course.setId(dto.getId());
//        course.setTitle(dto.getTitle());
//        course.setPrice(dto.getPrice());

        // we can directly map through model mapper class(source, destination)
        Course course = modelMapper.map(dto, Course.class);
        return course;
    }


}
