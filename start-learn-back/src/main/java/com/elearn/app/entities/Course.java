package com.elearn.app.entities;

import com.elearn.app.dtos.CategoryDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Data // Getter() + setter() + toString() all together
public class Course {

    @Id
    private String id;
    private String title;
    private String shortDesc;
    @Column(length = 2000, columnDefinition = "TEXT")
    private String longDesc;
    private double price;
    private boolean live = false;
    private double discount;
    private Date createdDate;
    private String banner;
    private String bannerContentType;

    // add more fields

    // videos
    @OneToMany(mappedBy = "course")
    private List<Video> videoList = new ArrayList<>();

    @ManyToMany
    private List<Category> categoryList = new ArrayList<>();

    public void addCategory(Category category){
        categoryList.add(category);

        category.getCourseList().add(this);
    }

    public void removeCategory(Category category){
        categoryList.remove(category);
        category.getCourseList().remove(this);
    }
}
