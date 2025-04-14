package com.elearn.app.dtos;

import com.elearn.app.entities.Role;
import lombok.Data;

import java.util.*;

@Data
public class UserDto {
    private String userId;

    private String userName;

    private String about;

    private String email;

    private String phoneNumber;

    private boolean active;

    private boolean emailVerified;

    private String password;

    private String profilePath;

    private boolean smsVerified;

    private Date createdAt;

    private String recentOtp;

    private Set<RoleDto> roles = new HashSet<>();
}

