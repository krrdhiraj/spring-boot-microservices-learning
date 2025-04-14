package com.boot.jpa.ecom.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "jpa_products")
public class Products {

    @Id
    @Column(name = "prod_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

//    @Column(name = "product_title", unique = true, nullable = false, length = 500)
    private String title;

    private String description;

    private double price;

    private boolean isLive = false;

    @OneToOne
    private   Category category;

    public Products(int id, boolean isLive, double price, String description, String title) {
        this.productId = id;
        this.isLive = isLive;
        this.price = price;
        this.description = description;
        this.title = title;
    }

    public Products() {

    }

    public int getId() {
        return productId;
    }

    public void setId(int id) {
        this.productId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + productId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", isLive=" + isLive +
                '}';
    }
}
