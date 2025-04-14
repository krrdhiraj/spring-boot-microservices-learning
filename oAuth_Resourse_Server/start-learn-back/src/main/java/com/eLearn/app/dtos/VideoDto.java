package com.eLearn.app.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VideoDto {

        private String videoId;

        private String title;

        private String desc;

        private String contentType;

        private String filePath;

        private String fileSize;

        private Date createdDt;

        public String getBannerUrl(){
                return "http://localhost:8081/api/v1/videos/"+videoId+"/banner";
        }

//        private Course course;

}
