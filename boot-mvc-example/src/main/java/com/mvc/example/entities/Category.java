package com.mvc.example.entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "jpa_Catgry")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @Column(name = "desc", nullable = false, unique = true)
    private String description;

    private String title;

    public  Category(){

    }
    public Category(int id, String description, String title) {
        this.id = id;
        this.description = description;
        this.title = title;
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
