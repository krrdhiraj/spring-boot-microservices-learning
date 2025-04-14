package com.spring.jdbc.ecom.dao;

import com.spring.jdbc.ecom.model.Product;
import com.spring.jdbc.ecom.model.ProductWithCategory;

import java.util.List;

public interface ProductDao {
    // save product
    Product create(Product product);

    // update product
    Product update(Product product, int productId);

    // delete Product
    void delete(int productId);

    // get all product
    List<Product> getAll();

    // get single product
    Product getSingleProduct(int productId);

    // search product
    List<Product> search(String keyword);

    // get all product of category
    List<Product> getAllCategory();

    List<ProductWithCategory> getAllWithCategory();
}