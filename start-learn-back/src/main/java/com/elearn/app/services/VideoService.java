package com.elearn.app.services;

import com.elearn.app.dtos.CustomPageResponse;
import com.elearn.app.dtos.VideoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VideoService {
    VideoDto create(VideoDto videoDto);

    List<VideoDto> getAll();

    VideoDto getSingleVideo(String videoId);

    ResponseEntity<?> deleteVideo(String videoId);

    VideoDto updateVideo(VideoDto videoDto, String videoId);

    Page<VideoDto> getVideoPages(Pageable pageable);

    List<VideoDto> searchVideos(String keyword);
}
