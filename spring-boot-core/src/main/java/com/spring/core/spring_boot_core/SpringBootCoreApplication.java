package com.spring.core.spring_boot_core;

import com.spring.core.spring_boot_core.comp.Father;
import com.spring.core.spring_boot_core.comp.Student;
import com.spring.core.spring_boot_core.controller.HomeController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import substring.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ComponentScan(basePackages = {"substring", "com.spring.core.spring_boot_core"})
public class SpringBootCoreApplication {

	public static void main(String[] args) throws InterruptedException {

		// bootstraping your application
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootCoreApplication.class, args);
//		ApplicationContext applContext = new AnnotationConfigApplicationContext(AppConfig.class);
//		Student student = applicationContext.getBean(Student.class);
//		System.out.println(student);
//		student.study();
//		Car car = applicationContext.getBean(Car.class);
//		System.out.println(car);
		Father father =  applicationContext.getBean(Father.class);
		father.useStudent();
		father.getTest();
//
//		HomeController homeController = applicationContext.getBean(HomeController.class);
//		homeController.loginUser();
//		homeController.logoutUser();

//		Car bean1 = applicationContext.getBean(Car.class);
//		Car bean2 = applicationContext.getBean(Car.class);
//		Car bean3 = applicationContext.getBean(Car.class);
//		System.out.println(bean3);
//		System.out.println(bean2);
//		System.out.println(bean1);

	}

}
