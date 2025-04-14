package com.boot.jpa.ecom.Interface;

import com.boot.jpa.ecom.entities.Products;

import java.util.List;

public interface IProductService {

    // Create Product
    public Products create(Products products);

    // Get All products
    public  List<Products> getAllProducts();

    // Find products by ID
    public Products byId(int id);

    // delete the products
    public void delete(int productId);

}
