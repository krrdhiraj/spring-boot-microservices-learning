package com.boot.jpa.ecom;

import com.boot.jpa.ecom.entities.Category;
import com.boot.jpa.ecom.entities.Products;
import com.boot.jpa.ecom.services.CategoryService;
import com.boot.jpa.ecom.services.ProductService;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
// we can put EnableJpaRepositories in the main class, it will scan all the package
//@EnableJpaRepositories(basePackages = {"in.repositories","com.boot.jpa.ecom"} )
//public class SpringJpaEcomApplication implements CommandLineRunner {
public class SpringJpaEcomApplication {

//	@Autowired
//	private ProductService productService;
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SpringJpaEcomApplication.class, args);
//		ProductService productService = context.getBean(ProductService.class);
		CategoryService categoryService = context.getBean(CategoryService.class);

		Category category = new Category();
		category.setTitle("IPhone");
		category.setDescription("Best Selling Iphone of the since 2023.");
		categoryService.create(category);

//		categoryService.delete(3);
//		List<Category> allCategory = categoryService.getAllCategory();
//		System.out.println(allCategory.toString());

//		Products products = new Products();
//		products.setTitle("Nothing 2");
//		products.setDescription("Best android phone with amazing features.");
//		products.setLive(true);
//		products.setPrice(29999);

//		bean.create(products);
//		bean.update(1,products);
		System.out.println("-----------------------All Products are displayed below.---------------------------------------");
//		List<Products> allProducts = bean.getAllProducts();
//		allProducts.forEach(products1 -> System.out.println(products1));
//		System.out.println("Products updated successfully!");
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Application is started....");
//		Products product = new Products();
//        product.setTitle("IQOO Z3");
//        product.setDescription("this is a good Android phone");
//        product.setPrice(19999);
//        product.setLive(false);
//        Products product1 = productService.create(product);
//        System.out.println(product1);
//        System.out.println("product created:");

//		productService.getAllProducts().forEach(System.out::println);

//		System.out.println(productService.byId(3));
//	}
}
