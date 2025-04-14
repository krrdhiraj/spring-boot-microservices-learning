package com.eLearn.app.config.security;

import com.eLearn.app.config.CustomAuthenticationEntryPoint;
import com.eLearn.app.dtos.CustomMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomConverter customConverter;

    public SecurityConfig(CustomAuthenticationEntryPoint authenticationEntryPoint, CustomConverter customConverter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.customConverter = customConverter;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthConverter(){
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        // set the convert to jwtAuthentication converter
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(customConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors(cor -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:4200"));
            configuration.addAllowedHeader("*");
            configuration.addAllowedMethod("*");
            configuration.setAllowCredentials(true);
            configuration.setMaxAge(300L);

            UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
            configurationSource.registerCorsConfiguration("/**", configuration);

            cor.configurationSource(configurationSource);
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(auth ->
                auth.requestMatchers("/doc.html", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**").permitAll()
                        .requestMatchers("/api/v1/auth/login", "/test/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("ADMIN", "GUEST")
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        );
        httpSecurity.sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.exceptionHandling(e ->
                e.authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                            CustomMessage customMessage = new CustomMessage();
                            customMessage.setMessage("You are not permitted to perform the operation : " + accessDeniedException.getMessage());
                            customMessage.setSuccess(false);
                            customMessage.setStatus(HttpStatus.FORBIDDEN);

                            String message = new ObjectMapper().writeValueAsString(customMessage);
                            response.getWriter().println(message);
                        })
        );
        httpSecurity.logout(logout -> {
            logout.logoutUrl("/logout");
        });

        // configuration of authorization server
        httpSecurity.oauth2ResourceServer(server -> {
//            server.jwt(Customizer.withDefaults()); // default configuration by spring
            server.jwt(jwtConfigurer -> {  // custom configuration
                jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter());
            });
        });

        return httpSecurity.build();
    }
}
