package com.elearn.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    private String id;

    private String title;

    @Column(name = "description", nullable = true)
    private String desc;
    private Date addedDate;

    // multiple course
    @ManyToMany(mappedBy = "categoryList",cascade = CascadeType.ALL)
    private List<Course> courseList = new ArrayList<>();

    public void addCourse(Course course){
        // add course to current list of course
        courseList.add(course);

        // new course ki category list me add kiya h current category ko
        course.getCategoryList().add(this);
    }
    public void removeCourse(Course course){
        courseList.remove(course);
        course.getCategoryList().remove(this);
    }
}
