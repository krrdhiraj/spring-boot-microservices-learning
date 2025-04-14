package com.eLearn.app.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

    @Id
    private String id;

    @Column(nullable = true)
    private String title;

    private String description;

    private Date addedDate;

//    @OneToMany(mappedBy = "category")
//    private List<Course> courseList = new ArrayList<>();

    @ManyToMany(mappedBy = "categoryList",cascade = CascadeType.ALL)
    private List<Course> courseList = new ArrayList<>();

    public void addCourse(Course course){
        // couseList me course add kiya
        courseList.add(course);

        // course ki category list mein add kiya current category
        course.getCategoryList().add(this);
    }

    public void removeCourse(Course course){

        courseList.remove(course);

        course.getCategoryList().remove(this);
    }

}
