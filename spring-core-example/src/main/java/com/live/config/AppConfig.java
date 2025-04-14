package com.live.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.live.components.Wheel;

@Configuration
@ComponentScan(basePackages = {"com.live.components"})
public class AppConfig {

	// Create beans
//	@Bean
//	public Wheel wheel() {
//		return new Wheel();
//	}
}
