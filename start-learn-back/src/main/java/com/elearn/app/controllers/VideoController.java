package com.elearn.app.controllers;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.CustomMessage;
import com.elearn.app.dtos.CustomPageResponse;
import com.elearn.app.dtos.VideoDto;
import com.elearn.app.services.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

    private final VideoService videoService;
    public VideoController(VideoService videoService){
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<VideoDto> create(
            @RequestBody VideoDto videoDto
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.create(videoDto));
    }
    @GetMapping
    public List<VideoDto> getAll(){
        return videoService.getAll();
    }
    @GetMapping("/{videoId}")
    public VideoDto getSingleVideo(
            @PathVariable String videoId
    ){
        return videoService.getSingleVideo(videoId);
    }

    @GetMapping("/pages")
    public Page<VideoDto> getVideos(Pageable pageable){
        return videoService.getVideoPages(pageable);
    }

    @DeleteMapping("/{videoId}")
    public ResponseEntity<CustomMessage> deleteVideo(
            @PathVariable String videoId
    ){
        videoService.deleteVideo(videoId);
        CustomMessage customMessage = new CustomMessage("video deleted successfully.",true);
        return ResponseEntity.ok(customMessage);
    }
    @PutMapping("/{videoId}")
    public VideoDto updateVideo(
            @RequestBody VideoDto videoDto,
            @PathVariable String videoId
    ){
        return videoService.updateVideo(videoDto, videoId);
    }
    @GetMapping("/search")
    public ResponseEntity<List<VideoDto>> searchVideos(@RequestParam String keyword){
        return ResponseEntity.ok(videoService.searchVideos(keyword));
    }
}
