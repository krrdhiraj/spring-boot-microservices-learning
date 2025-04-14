package com.eLearn.app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDto {

    private String id;

    @NotEmpty(message = "title is required !!!")
    @Size(min = 3, max = 50, message = "title must be lies between 3 and 50 characters!")
    private String title;

    @NotEmpty(message = "description is necessary !!!")
    private String description;

//    @Email // for email validation
//    @Pattern( regex = "") // this is for custom validation
@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy hh:mm:ss a", timezone = "IST")
    private Date addedDate;

//    private List<Course> courseList = new ArrayList<>();
}
