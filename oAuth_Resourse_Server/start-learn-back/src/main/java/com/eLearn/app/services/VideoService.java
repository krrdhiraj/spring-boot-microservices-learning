package com.eLearn.app.services;

import com.eLearn.app.dtos.CustomPageResponse;
import com.eLearn.app.dtos.ResourceContentType;
import com.eLearn.app.dtos.VideoDto;
import com.eLearn.app.entites.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoService {


    VideoDto insert(VideoDto videoDto);

    List<VideoDto> getAllVideo();

    VideoDto getSingleVideo(String videoId);

    VideoDto update(String videoId, VideoDto videoDto);

    void delete(String videoId);

    CustomPageResponse<VideoDto> getAllPages(int pageNumber, int pageSize, String sortBy);

    List<VideoDto> search(String keyword);

    Page<VideoDto> getAllVideos(Pageable pageable);

    void addVideoToCourse(String videoId, String courseId);

    VideoDto saveBanner(MultipartFile file, String videoId) throws IOException;

    ResourceContentType getVideoBannerById(String videoId);

}
