package com.eLearn.app.entites;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "video")
@Data
public class Video {

    @Id
    private String videoId;

    private String title;

    @Column(name = "description", length = 1000)
    private String desc;

    private String contentType;

    private String filePath;

    private String fileSize;

    private Date createdDt;

    @ManyToOne
    private Course course;
}
