package com.eLearn.app.controllers;

import com.eLearn.app.config.AppConstants;
import com.eLearn.app.dtos.CustomPageResponse;
import com.eLearn.app.dtos.ResourceContentType;
import com.eLearn.app.dtos.VideoDto;
import com.eLearn.app.services.VideoService;
import org.hibernate.engine.spi.Resolution;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

    private VideoService videoService ;

    private ModelMapper modelMapper;

    public VideoController(VideoService videoService, ModelMapper modelMapper) {
        this.videoService = videoService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<?> insertVideo(
            @RequestBody VideoDto videoDto
    ){
        VideoDto insertVid = videoService.insert(videoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertVid);
    }
    @GetMapping
    public List<?> getAllVideo(){
        List<VideoDto> allVideo = videoService.getAllVideo();
        return allVideo;
    }
    @GetMapping("/{videoId}")
    public VideoDto getSingle(
            @PathVariable String videoId
    ){
        VideoDto singleVideo = videoService.getSingleVideo(videoId);
        return singleVideo;
    }

    @GetMapping("/allPages")
    public CustomPageResponse<?> getAllPages(
            @RequestParam(name = "pageNumber", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy
    ){
        CustomPageResponse<VideoDto> allPages = videoService.getAllPages(pageNumber, pageSize, sortBy);
        return allPages;
    }
    @PutMapping("/{videoId}")
    public VideoDto updateVideo(
            @PathVariable String videoId,
            @RequestBody VideoDto videoDto
    ){
        VideoDto singleVideo = videoService.getSingleVideo(videoId);
        singleVideo.setTitle(videoDto.getTitle());
        singleVideo.setDesc(videoDto.getDesc());
        singleVideo.setFilePath(videoDto.getFilePath());
        singleVideo.setFileSize(videoDto.getFileSize());
        singleVideo.setContentType(videoDto.getContentType());
        videoService.insert(singleVideo);
        return singleVideo;
    }

    @DeleteMapping("/{videoId}")
    public void deleteVideo(
            @PathVariable String videoId
    ){
        videoService.delete(videoId);
    }

    @PostMapping("/{videoId}/banner")
    public ResponseEntity<?> uploadVideoBanner(
            @PathVariable String videoId,
            @RequestParam MultipartFile file
            ) throws IOException {
        return ResponseEntity.ok().body(videoService.saveBanner(file, videoId));
    }

    @GetMapping("/{videoId}/banner")
    public ResponseEntity<?> getBannerOfVideo(
            @PathVariable String videoId
    ){
        ResourceContentType resourceContentType = videoService.getVideoBannerById(videoId);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(resourceContentType.getContentType())).body(resourceContentType.getResource());
    }
}
