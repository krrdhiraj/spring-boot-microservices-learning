package com.eLearn.app.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
public class Course {

    @Id
    private String id;

    private String title;

    private String shortDesc;

    @Column(length = 2000)
    private String longDesc;

    private double price;

    private boolean live=false;

    private double discount;

    private Date createdDate;

    private String banner;

    private String bannerContentType;

    @OneToMany(mappedBy = "course",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video){
        videos.add(video);
//        video.getCourse().addVideo(video);
    }

    public void removeVideo(Video video){
        videos.remove(video);
//        video.getCourse().removeVideo(video);
    }

    @ManyToMany
    private List<Category> categoryList = new ArrayList<>();

    public void removeCategory(Category category){

        categoryList.remove(category);
        category.getCourseList().remove(this);
    }
    public void addCategory(Category category){
        categoryList.add(category);
        category.getCourseList().add(this);
    }
}
