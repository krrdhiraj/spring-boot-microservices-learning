package com.elearn.app.services;

import com.elearn.app.dtos.VideoDto;
import com.elearn.app.entities.Video;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.VideoRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService{

    private ModelMapper modelMapper;
    private VideoRepo videoRepo;

    @Override
    public VideoDto create(VideoDto videoDto) {
        String id = UUID.randomUUID().toString();
        videoDto.setVideoId(id);
        videoDto.setAddedDate(new Date());
        Video video = modelMapper.map(videoDto, Video.class);
        Video savedVideo = videoRepo.save(video);
        return modelMapper.map(savedVideo,VideoDto.class);
    }

    @Override
    public List<VideoDto> getAll() {
        List<Video> videoList = videoRepo.findAll();
        return videoList.stream().map(video -> modelMapper.map(video,VideoDto.class)).toList();
    }

    @Override
    public VideoDto getSingleVideo(String videoId) {
        Video video = videoRepo.findById(videoId).orElseThrow(() ->
                new ResourceNotFoundException("No video found with this id."));
        return modelMapper.map(video, VideoDto.class);
    }

    @Override
    public ResponseEntity<?> deleteVideo(String videoId) {
        Video video = videoRepo.findById(videoId).orElseThrow(() ->
                new ResourceNotFoundException("No video found to delete."));

        return ResponseEntity.ok(video);
    }

    @Override
    public VideoDto updateVideo(VideoDto videoDto, String videoId) {
        Video video = videoRepo.findById(videoId).orElseThrow(() ->
                new ResourceNotFoundException("No video found to update with this id :- ." + videoId));
        modelMapper.map(videoDto,video);
//        video.setPath(videoDto.getPath());
//        video.setDesc(videoDto.getDesc());
//        video.setLength(videoDto.getLength());
//        video.setCourse(videoDto.getCourse());
//        video.setBanner(videoDto.getBanner());
//        video.setTitle(videoDto.getTitle());
//        video.setContentType(videoDto.getContentType());

        Video savedVideo = videoRepo.save(video);
        return modelMapper.map(savedVideo, VideoDto.class);
    }

    @Override
    public Page<VideoDto> getVideoPages(Pageable pageable) {

        Page<Video> videoPage = videoRepo.findAll(pageable);
        List<Video> videoList = videoPage.getContent();
        List<VideoDto> videoDtoList = videoList.stream().map(video ->
                modelMapper.map(video, VideoDto.class)).toList();
        return new PageImpl<>(videoDtoList, pageable, videoPage.getTotalElements());
    }

    @Override
    public List<VideoDto> searchVideos(String keyword) {
        List<Video> videoList = videoRepo.findByTitleContainingIgnoreCaseOrDescContainingIgnoreCase(keyword,keyword);

        return videoList.stream().map(video ->
                modelMapper.map(video,VideoDto.class)).collect(Collectors.toList());
    }

}
