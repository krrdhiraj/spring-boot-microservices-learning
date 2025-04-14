package com.eLearn.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StartLearnBackApplication  implements CommandLineRunner {


	public static void main(String[] args) {

		SpringApplication.run(StartLearnBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
