package com.eLearn.app.config.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {

        // taking out realmAccess object which will provide roles
        Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");

        List<String> roles = (List<String>) realmAccess.get("roles");

        List<GrantedAuthority> list = roles.stream().map(role ->
                new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());

        return list;
    }
}
