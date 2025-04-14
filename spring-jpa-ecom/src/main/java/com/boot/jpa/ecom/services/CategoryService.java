package com.boot.jpa.ecom.services;

import com.boot.jpa.ecom.entities.Category;
import com.boot.jpa.ecom.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(Category category){
        Category savedCategory = categoryRepository.save(category);
        return savedCategory;
    }

    public List<Category> getAllCategory(){
        return  categoryRepository.findAll();
    }

    public Category getSingleCategory(int catId){
        return categoryRepository.findById(catId).
                orElseThrow(()->
                        new RuntimeException("No Category are associated with this Id : " + catId));
    }
    public void delete(int catId){
        Category category = categoryRepository.findById(catId).orElseThrow(() -> new RuntimeException("No category found!"));
        categoryRepository.delete(category);
    }
}
