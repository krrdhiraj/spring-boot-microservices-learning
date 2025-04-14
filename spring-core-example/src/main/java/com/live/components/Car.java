package com.live.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
	
	// Field Injection --> it will inject the obj of wheel to car
//	@Autowired
	Wheel wheel ;
//	@Autowired
	Engine engine;
	
	// Constructor Injection --> spring 4.3 k bad @Autowired likhe bina hi inject krr dega
//	public Car(Wheel wheel, Engine engine) {
//		super();
//		this.wheel = wheel;
//		this.engine = engine;
//	}
	
	
	public void driving() {
		System.out.println("Car is driving by me.");
		wheel.use();
		engine.start();
	}

	public Wheel getWheel() {
		return wheel;
	}

	// setting Injection --> by using setting method
	@Autowired
	public void setWheel(Wheel wheel) {
		System.out.println("Setting the wheel");
		this.wheel = wheel;
	}

	public Engine getEngine() {
		return engine;
	}

	@Autowired
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
}
