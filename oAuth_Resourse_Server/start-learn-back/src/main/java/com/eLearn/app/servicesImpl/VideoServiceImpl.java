package com.eLearn.app.servicesImpl;

import com.eLearn.app.Repositories.CourseRepo;
import com.eLearn.app.Repositories.VideoRepo;
import com.eLearn.app.config.AppConstants;
import com.eLearn.app.dtos.CustomPageResponse;
import com.eLearn.app.dtos.ResourceContentType;
import com.eLearn.app.dtos.VideoDto;
import com.eLearn.app.entites.Course;
import com.eLearn.app.exceptions.ResourceNotFoundException;
import com.eLearn.app.services.FileService;
import com.eLearn.app.services.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.eLearn.app.entites.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {

    VideoRepo videoRepo;
    ModelMapper modelMapper ;
    CourseRepo courseRepo;
    FileService fileService;

    public VideoServiceImpl(VideoRepo videoRepo, ModelMapper modelMapper,CourseRepo courseRepo, FileService fileService) {
        this.videoRepo = videoRepo;
        this.modelMapper = modelMapper;
        this.courseRepo = courseRepo;
        this.fileService = fileService;
    }

    @Override
    public VideoDto insert(VideoDto videoDto) {
        String id = UUID.randomUUID().toString();
        videoDto.setVideoId(id);
        videoDto.setCreatedDt(new Date());
        Video mapVideo = modelMapper.map(videoDto, Video.class);
        Video savedVideo = videoRepo.save(modelMapper.map(videoDto, Video.class));
        return modelMapper.map(savedVideo,VideoDto.class);
    }

    @Override
    public List<VideoDto> getAllVideo() {
        return videoRepo.findAll().stream().map(video -> modelMapper.map(video,VideoDto.class)).toList();
//        return Collections.singletonList(modelMapper.map(videoRepo.findAll().stream().toList(), VideoDto.class));
    }

    @Override
    public VideoDto getSingleVideo(String videoId) {

        return modelMapper.map(videoRepo.findById(videoId).orElseThrow(() -> new ResourceNotFoundException("No video present with this id : "+ videoId)),VideoDto.class);
    }

    @Override
    public VideoDto update(String videoId, VideoDto videoDto) {
        Video video = videoRepo.findById(videoId).
                orElseThrow(() -> new ResourceNotFoundException("No video found!!"));
        video.setTitle(videoDto.getTitle());
        video.setDesc(videoDto.getDesc());
        video.setFilePath(videoDto.getFileSize());
        video.setContentType(videoDto.getContentType());
        video.setFileSize(videoDto.getFileSize());

        Video saved = videoRepo.save(video);

        return modelMapper.map(saved,VideoDto.class);
    }

    @Override
    public void delete(String videoId) {
        Video video = videoRepo.findById(videoId).
                orElseThrow(() -> new ResourceNotFoundException("No video found!!"));
        videoRepo.delete(video);
    }

    @Override
    public CustomPageResponse<VideoDto> getAllPages(int pageNumber, int pageSize, String sortBy) {

        Sort sort = Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<Video> pager = videoRepo.findAll(pageRequest);
        List<Video> allVideo = pager.getContent();

        List<VideoDto> videoDtoList = allVideo.stream().map(video -> modelMapper.map(video, VideoDto.class)).toList();
        CustomPageResponse<VideoDto> customPageResponse = new CustomPageResponse<>();

        customPageResponse.setPageNumber(pageNumber);
        customPageResponse.setPageSize(pageSize);
        customPageResponse.setTotalPages(pager.getTotalPages());
        customPageResponse.setContent(videoDtoList);
        customPageResponse.setLast(pager.isLast());

        return customPageResponse;
    }

    @Override
    public List<VideoDto> search(String keyword) {
        List<Video> videoList = videoRepo.findByTitleContainingIgnoreCaseOrDescContainingIgnoreCase(keyword, keyword);
        return videoList.stream().map(video -> modelMapper.map(video,VideoDto.class)).toList();
    }

    @Override
    public Page<VideoDto> getAllVideos(Pageable pageable) {
        Page<Video> videos = videoRepo.findAll(pageable);
        List<VideoDto> videoDtos = videos.getContent().stream().map(video -> modelMapper.map(video, VideoDto.class)).toList();
        return new PageImpl<>(videoDtos, pageable, videos.getTotalElements());
    }

    @Override
    public void addVideoToCourse(String videoId, String courseId) {
        Video video = videoRepo.findById(videoId).orElseThrow(() -> new ResourceNotFoundException("No Video found!"));
        Course course = courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("No course found!!"));
        course.addVideo(video);
        courseRepo.save(course);
        System.out.println("Course updated : " + new Date());
    }

    @Override
    public VideoDto saveBanner(MultipartFile file, String videoId) throws IOException {

        Video video = videoRepo.findById(videoId).orElseThrow(() -> new ResourceNotFoundException("No Video found!"));
        String filePath = fileService.saveFile(file, AppConstants.COURSE_BANNER_UPLOAD_DIR, file.getOriginalFilename());
        video.setFilePath(filePath);
        video.setFileSize(String.valueOf(file.getSize()));
        video.setContentType(file.getContentType());
        return modelMapper.map(videoRepo.save(video),VideoDto.class);
    }

    @Override
    public ResourceContentType getVideoBannerById(String videoId) {
        Video video = videoRepo.findById(videoId).orElseThrow(() -> new ResourceNotFoundException("No Video found!"));
        Path path = Paths.get(video.getFilePath());

        Resource resource = new FileSystemResource(path);
        ResourceContentType resourceContentType = new ResourceContentType();
        resourceContentType.setResource(resource);
        resourceContentType.setContentType(video.getContentType());
        return resourceContentType;
    }
}
