package com.photos.oAuth.seurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(request->
                request
                        .requestMatchers("/photos").authenticated()
                        .anyRequest().permitAll())
                        .formLogin(Customizer.withDefaults())
//                        .oauth2Login(Customizer.withDefaults())
//                        .oauth2Client(Customizer.withDefaults())
                        .oauth2Login(oauth-> {
                            oauth
                                    .successHandler((request, response, authentication) -> {
                                        System.out.println("OAuth login success");
                                        System.out.println(authentication.toString());
                                        response.sendRedirect("/");
                                        if (authentication instanceof OAuth2AuthenticationToken) {
                                            OAuth2User user = (OAuth2User) authentication.getPrincipal();
                                            String loginuser = user.getAttribute("login");
                                            String email = user.getAttribute("email");
                                            System.out.println("UserName : " + loginuser + " with email id : " + email);
                                        }
                                    })
                                    .failureHandler((request, response, exception) -> {
                                        System.out.println("Login error");
                                        exception.printStackTrace();
                                        response.sendRedirect("/");
                                    });
                        });

        return httpSecurity.build();
    }
}
