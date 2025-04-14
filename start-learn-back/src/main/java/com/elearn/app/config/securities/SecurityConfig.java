package com.elearn.app.config.securities;

import com.elearn.app.dtos.CustomMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.print.attribute.standard.Media;
import java.lang.runtime.ObjectMethods;
import java.util.List;

@Configuration
//@EnableMethodSecurity(prePostEnabled = true) // for enabling method authorization(assign role on method or controller)
@EnableWebSecurity(debug = true) // for enabling debugging
public class SecurityConfig {
    @Autowired
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
      return  configuration.getAuthenticationManager();
    }

    // In Memory user management like create an user nd password for login purpose
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//
//        // now create an user
//        userDetailsManager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .username("ram")
//                        .password("ram")
//                        .roles("ADMIN")
//                        .build()
//        );
//        userDetailsManager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .username("shiva")
//                        .password("shiva")
//                        .roles("CUSTOMER")
//                        .build()
//        );
//        return userDetailsManager;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // customization

        // give our own route/urls

        // CORS Configuration
//        httpSecurity.cors(AbstractHttpConfigurer::disable);
        httpSecurity.cors(cor ->{
            CorsConfiguration corsConfiguration = new CorsConfiguration();
//            corsConfiguration.addAllowedOrigin("http://localhost:5173");
            corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:9073"));
            corsConfiguration.addAllowedHeader("*");
            corsConfiguration.addAllowedMethod("*");
            corsConfiguration.setMaxAge(300L);
            corsConfiguration.setAllowCredentials(true);
            UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
            configurationSource.registerCorsConfiguration("/**", corsConfiguration);

            cor.configurationSource(configurationSource);

        });

        httpSecurity.csrf(e->e.disable());

        // Authorization with Roles/Authority
        httpSecurity.authorizeHttpRequests(auth->
                auth
                        .requestMatchers("/api-doc.html","/v3/api-docs/**", "/swagger-ui/**","/swagger-resources/**").permitAll()
                        .requestMatchers("/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/v1/**").hasAnyRole("ADMIN","GUEST")
                        .requestMatchers(HttpMethod.POST, "api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "api/v1/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        );

//      Jwt token filter
        httpSecurity.sessionManagement(e->
                e.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


//         Handeling Authentication Exception
        httpSecurity.exceptionHandling(ex->
                ex.authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                            CustomMessage customMessage = new CustomMessage();
                            customMessage.setMessage("You don't have permission to access this API's");
                            customMessage.setStatus(false);

                            ObjectMapper objectMapper = new ObjectMapper();
                            response.getWriter().println(objectMapper.writeValueAsString(customMessage));
                        })
        );

        // for generating particular exception user httpBasic
//        httpSecurity.httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(customAuthenticationEntryPoint));

        // basic login pages with Customizer
//        httpSecurity.httpBasic(Customizer.withDefaults());

        // customize form base login
//        httpSecurity.formLogin(form -> {
//            form.loginPage("/client-login");
//            form.usernameParameter("userName");
//            form.passwordParameter("userPassword");
//            form.loginProcessingUrl("/client-login-process");
//            form.successForwardUrl("/success");
////            form.successHandler("success");
////            form.failureHandler();
//        });
//         form based login defaults
//        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
