package com.boot.jpa.ecom.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "jpa_Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    private String title;

    @OneToOne
    private Products products;

    public Category() {
    }

    public Category(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +

                '}';
    }
}
