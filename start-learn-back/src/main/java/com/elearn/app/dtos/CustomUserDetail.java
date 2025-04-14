package com.elearn.app.dtos;

import com.elearn.app.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class CustomUserDetail implements UserDetails {

    User user = new User();

    public User getUser() {
        return user;
    }

    public CustomUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // create and return user's role/Authority
        return user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
