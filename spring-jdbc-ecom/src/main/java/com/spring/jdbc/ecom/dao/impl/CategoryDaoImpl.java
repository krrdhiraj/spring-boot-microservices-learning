package com.spring.jdbc.ecom.dao.impl;

import com.spring.jdbc.ecom.dao.CategoryDao;
import com.spring.jdbc.ecom.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        String query = "create table if not exists categories(id int primary key, title varchar(200) not null , description varchar(500) not null)";
        int updated = jdbcTemplate.update(query);
        System.out.println(updated + " rows updated and Category table created.");
    }
    @Override
    public Category create(Category category) {
        int update = jdbcTemplate.update("insert into categories(id,title,description) values(?,?,?)", category.getId(), category.getTitle(), category.getDescription());
        System.out.println(update + "categories added");
        return category;
    }
}
