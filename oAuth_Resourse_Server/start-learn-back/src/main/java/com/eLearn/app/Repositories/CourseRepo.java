package com.eLearn.app.Repositories;

import com.eLearn.app.dtos.CourseDto;
import com.eLearn.app.entites.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course,String> {
    List<Course> findByTitle(String title);

    List<Course> findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(String keyword, String keyword1);

//   List<Course> findByLive(boolean live);

//    @Query("select c from Course c where c.category.i")
//    List<Course> findByCategoryId(String categoryId);

//    List<Course> findByLongDescContainingIgnoreCase(String keyword);
}
