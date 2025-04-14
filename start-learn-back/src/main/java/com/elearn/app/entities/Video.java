package com.elearn.app.entities;

import com.elearn.app.dtos.CourseDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "videos")
public class Video {

    @Id
    private String videoId;
    private String title;

    @Column(name = "description")
    private String desc;

    private String banner;

    private String length;

    private String fileSize;

    private String path;

    private String contentType;

    private Date addedDate;

    @ManyToOne
    private Course course ;

}
