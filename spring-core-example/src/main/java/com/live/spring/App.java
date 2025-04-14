package com.live.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.live.components.Car;
import com.live.components.Engine;
import com.live.components.Wheel;
import com.live.config.AppConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// project --> package
    	// artifact id --> project name 
    	
//    	Car car = new Car();
//    	Wheel wheel = new Wheel();
    	
//    	wheel.use();
//    	car.driving();
    	
    	// how can we get the objects from spring container
    	// access spring container
    	
    	// BeanFactory -> from spring 3 , it's deprecated
    	// ApplicationContext --> An interface represent spring container
    	
    	// XML Based Configuration
    	ApplicationContext applContext = new ClassPathXmlApplicationContext("com/live/spring/Config.xml");
//    	Wheel wheel = applContext.getBean(Wheel.class); 
//        wheel.use();
//        System.out.print(wheel);
       
        // Annotation based configuration
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        Wheel wheel = applicationContext.getBean(Wheel.class);
//        wheel.use();
//        
//        Engine engine = applicationContext.getBean(Engine.class);
//        engine.start();
//        System.out.println(wheel);
//        System.out.println(engine);
        // Kisi Class ko v bean banana h to usspe (@Component) annotation lga do
        
        // now car
        Car car = applicationContext.getBean(Car.class);
        car.driving();
    }
}
