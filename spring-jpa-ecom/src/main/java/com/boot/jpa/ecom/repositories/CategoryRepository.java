package com.boot.jpa.ecom.repositories;

import com.boot.jpa.ecom.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Agar @Repository nhi v likhte h to kam chal jayega
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
