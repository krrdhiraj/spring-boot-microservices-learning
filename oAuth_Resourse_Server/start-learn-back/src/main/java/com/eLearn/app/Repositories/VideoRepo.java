package com.eLearn.app.Repositories;

import com.eLearn.app.entites.Course;
import com.eLearn.app.entites.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepo extends JpaRepository<Video, String> {


    List<Video> findByTitle(String title);

    List<Video> findByCourse(Course course);

    List<Video> findByTitleContainingIgnoreCaseOrDescContainingIgnoreCase(String keyword1, String keyword2);

}
