package com.mvc.example.service;

import com.mvc.example.entities.Category;
import com.mvc.example.exception.ResourceNotFoundException;
import com.mvc.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
//    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(Category category){
        Category saveCategory = categoryRepository.save(category);
        return saveCategory;
    }

    public List<Category> getAllCategory(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }
    
    public Category findById(int id){
        Category catId =  categoryRepository.findById(id).orElseThrow(()->
            new ResourceNotFoundException("No Category exits with this id : " + id));
        return catId;
    }

}
