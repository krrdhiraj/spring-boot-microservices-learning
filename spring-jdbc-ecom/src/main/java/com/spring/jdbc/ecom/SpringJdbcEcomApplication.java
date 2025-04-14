package com.spring.jdbc.ecom;

import com.spring.jdbc.ecom.dao.CategoryDao;
import com.spring.jdbc.ecom.dao.ProductDao;
import com.spring.jdbc.ecom.model.Category;
import com.spring.jdbc.ecom.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbcEcomApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SpringJdbcEcomApplication.class, args);

//		Product product = new Product();
//		product.setId(104);
//		product.setTitle("iQOO Z3");
//		product.setDescription("This is the best Android phone.");
//		product.setPrice(22000);

		ProductDao productDao = context.getBean(ProductDao.class);
		CategoryDao categoryDao = context.getBean(CategoryDao.class);
//		Category category = new Category();
//		category.setId(1002);
//		category.setTitle("mobiles");
//		category.setDescription("IOS phones");

//		Product product = new Product();
//		product.setId(102);
//		product.setTitle("Vivo V23");
//		product.setDescription("This is the best Camera phone.");
//		product.setPrice(29000);
//		product.setCatId(1001);

//		categoryDao.create(category);
//		productDao.create(product);

//		List<Product> productList = bean.getAll();
//		Product singleProduct =  bean.getSingleProduct(104);
//		productList.forEach(item->System.out.println(item.getTitle() + " : " + item.getPrice() ));
//		System.out.println(singleProduct);
//		System.out.println(productList.toString());
//		Product product = new Product();
//		product.setTitle("Vivo V23");
//		product.setDescription("Great Camera Android phone");
//		product.setPrice(35000);
//		product.setId(102);
//		bean.create(product);

//		Product product1 = bean.update(product, 102);

//		List<Product> productList = bean.getAll();
//		productList.forEach(item->System.out.println(item.getTitle() + " : " + item.getPrice() ));
//		bean.delete(0);

		productDao.getAllWithCategory().forEach(System.out::println);
	}

}
