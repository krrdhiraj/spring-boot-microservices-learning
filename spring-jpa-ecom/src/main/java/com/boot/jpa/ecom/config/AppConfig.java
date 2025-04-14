package com.boot.jpa.ecom.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@ComponentScan(basePackages = {"in.repositories","com.boot.jpa.ecom"})
//@EnableJpaRepositories(basePackages = {"in.repositories","com.boot.jpa.ecom"} )
// @EnableJpaRepositories-> this will scan other repo & sub repo's also from other than main package
public class AppConfig {

}
