package com.boot.jpa.ecom.services;

import com.boot.jpa.ecom.entities.Products;
import com.boot.jpa.ecom.repositories.ProductRepository;
import com.boot.jpa.ecom.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

//    public ProductService(ProductRepository productRepository) {
//        System.out.println(productRepository);
//        System.out.println(productRepository.getClass().getName());
//        this.productRepository = productRepository;
//    }

    public Products create(Products products) {
        Products savedProduct = productRepository.save(products);
        return savedProduct;
    }

    public List<Products> getAllProducts() {
        List<Products> productsList = productRepository.findAll();
        return productsList;
    }


    public Products byId(int id) {
        // findById --> this will return an optional class
        Products byId = productRepository.findById(id).orElseThrow(()->
                new RuntimeException("No product present with this ID : "+id));
        return byId;
    }

    public void delete(int productId) {
        Products products = productRepository.findById(productId).get();
        productRepository.delete(products);
    }
    public Products update(int id, Products products){
        Products byIdProducts = productRepository.findById(id).orElseThrow(()->
                new RuntimeException("No product present with this ID : "+id));
        byIdProducts.setPrice(products.getPrice());
        byIdProducts.setTitle(products.getTitle());
        byIdProducts.setLive(products.isLive());
        byIdProducts.setDescription(products.getDescription());
        return  productRepository.save(byIdProducts);


    }

}
