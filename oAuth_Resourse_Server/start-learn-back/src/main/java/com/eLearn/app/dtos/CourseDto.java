package com.eLearn.app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourseDto{

    private String id;

    private String title;

    private String shortDesc;

//    @JsonIgnore
    private String longDesc;

    @JsonProperty("price of course")
    private double price;

    private boolean live=false;

    private double discount;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-dd-MM HH:mm:ss", timezone = "IST")
    private Date createdDate;

    private String banner;

//    @JsonIgnore
    private List<VideoDto> videos = new ArrayList<>();

    private List<CategoryDto> categoryList = new ArrayList<>();

    public String getBannerUrl(){
        return "http://localhost:8081/api/v1/courses/"+id+"/banner";
    }

}