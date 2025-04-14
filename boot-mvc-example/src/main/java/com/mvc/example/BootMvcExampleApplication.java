package com.mvc.example;

import com.mvc.example.entities.Category;
import com.mvc.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
public class BootMvcExampleApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(BootMvcExampleApplication.class, args);

//		CategoryService bean = context.getBean(CategoryService.class);
//		Category category1 = new Category(111,"Most trending phones of the market","Android Phones");
//		Category category2 = new Category(112,"Best flagship phones of 2024","Flagship Phones");
//		Category category3 = new Category(113,"Collections of IOS Phones","IOS Phones");
////		bean.create(category1);
//		System.out.println("Category is added to the table");
//		List<Category> allCategory = bean.getAllCategory();
//		System.out.println("..................All Category are shown below...........");
//		allCategory.forEach(category -> System.out.println(category));

	}

}
