package com.elearn.app.dtos;

import com.elearn.app.entities.Video;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {

    private String id;

    @NotEmpty(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;

    private String desc;

    private Date addedDate;

//    private List<CourseDto> courseList;
}
