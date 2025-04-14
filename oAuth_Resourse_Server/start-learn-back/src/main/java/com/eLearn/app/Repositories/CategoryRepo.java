package com.eLearn.app.Repositories;

import com.eLearn.app.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,String> {
}
