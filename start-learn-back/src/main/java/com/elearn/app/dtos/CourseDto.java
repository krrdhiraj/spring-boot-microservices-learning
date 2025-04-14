package com.elearn.app.dtos;

import com.elearn.app.entities.Category;
import com.elearn.app.entities.Video;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CourseDto {

    private String id;

    private String title;

    private String shortDesc;

//    @JsonIgnore // to ignore this , will not come in response data
    @JsonProperty("long_description") // give our own custom name
    private String longDesc;

    private double price;

    private boolean live = false;

    private double discount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/mm/dd HH:mm:ss",timezone = "IST")
    private Date createdDate;

    private String banner;

    private String bannerContentType;

//    private String bannerUrl;

    // videos
    private List<VideoDto> videoDtoList  = new ArrayList<>();

    private List<CategoryDto> categoryList = new ArrayList<>();

    public String getBannerUrl(){
        return "http://localhost:8080/api/v1/courses/"+id+"/banners";
    }
}
