package com.elearn.app.config.securities;

import com.elearn.app.services.CustomUserDetailService;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
//  Authorization : Bearer 32ew09sdiodiou439
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

//        System.out.println("Header : " + authorizationHeader);

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            // everything is fine
            jwtToken = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwtToken);

            // abhi koi authenticated nhi hai
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if(jwtUtil.validateToken(jwtToken, userDetails.getUsername())){

                    // authentication to security
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
//        else{
//            throw new AuthenticationException( "Token not present");
//        }
        filterChain.doFilter(request, response);
    }
}
