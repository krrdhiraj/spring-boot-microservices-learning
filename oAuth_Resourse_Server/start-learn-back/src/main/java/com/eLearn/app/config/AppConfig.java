package com.eLearn.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// this is for give every def about api
@OpenAPIDefinition(
        info = @Info(
                title = "Video-Streaming App",
                description = "Created by Dhiraj,Krish, Diksha & team",
                version = "1.0V",
                contact = @Contact(
                        name = "team streaming app",
                        email = "stream@teamStraming.com",
                        url="https://stramingapp.com"
                ),
                license = @License(
                        url = "https://stramingapp.com",
                        name = "Apache License 2.0"
                )
        ),
        // this will apply authorization on all controller
        security = @SecurityRequirement(name = "jwtScheme")
)
@SecurityScheme(
        name = "jwtScheme",
        type= SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
