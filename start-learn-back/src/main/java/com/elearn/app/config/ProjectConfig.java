package com.elearn.app.config;

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
import org.springframework.security.config.annotation.web.SecurityMarker;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "E-learn Application",
                description = "Created by Chickoo , Ayush , Ankur & team.",
                version = "1.0V",
                contact = @Contact(
                        name = "support E-learn App",
                        email = "support@elearnapp.com",
                        url = "https://elearnapp.com"
                ),
                license = @License(
                        url = "https://elerarnapp.com",
                        name = "Apache License 2.0"
                )
        ),
        security = @SecurityRequirement(name = "jwtScheme")
)
@SecurityScheme(
        name = "jwtScheme",
        type = SecuritySchemeType.HTTP,
        scheme = "Bearer",
        bearerFormat = "JWT"
)
public class ProjectConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    };
}
