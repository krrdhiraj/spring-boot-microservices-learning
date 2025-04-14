package com.boot.jpa.ecom.repositories;

import com.boot.jpa.ecom.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
    // CRUD operation

}
