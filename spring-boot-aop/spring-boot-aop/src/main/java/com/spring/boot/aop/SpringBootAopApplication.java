package com.spring.boot.aop;

import com.spring.boot.aop.bean.Company;
import com.spring.boot.aop.services.LoginService;
import com.spring.boot.aop.services.ProductService;
import com.spring.boot.aop.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootAopApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootAopApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =
				SpringApplication.run(SpringBootAopApplication.class, args);
//		Company bean = applicationContext.getBean(Company.class);
//		bean.work();

		LoginService loginService = applicationContext.getBean(LoginService.class);
//		loginService.logout();
//		loginService.doLogin();
//		loginService.logout();
//		loginService.mainLogic("admin","admin123");
		loginService.getAllUser("admin1","admin123");

//		ProductService productService = applicationContext.getBean(ProductService.class);
//		productService.printAllProduct();

//		UserService userService = applicationContext.getBean(UserService.class);
//		userService.createUser("Dhiraj Singhla");
	}

}