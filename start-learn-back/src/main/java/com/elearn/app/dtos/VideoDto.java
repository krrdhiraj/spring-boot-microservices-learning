package com.elearn.app.dtos;

import com.elearn.app.entities.Course;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class VideoDto {

    private String videoId;

    private String title;

    private String desc;

    private String banner;

    private String length;

    private String fileSize;

    private String path;

    private String contentType;

    private Date addedDate;

    private CourseDto course;

}
