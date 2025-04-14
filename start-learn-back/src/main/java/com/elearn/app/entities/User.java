package com.elearn.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.property.access.internal.PropertyAccessStrategyIndexBackRefImpl;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String userId;

    private String name;

    private String about;

    // userName = email
    @Column(unique = true)

    private String email;

    private String phoneNumber;

    private boolean active;

    private boolean emailVerified;

    private String password;

    private String profilePath;

    private boolean smsVerified;

    private Date createdAt;

    private String recentOtp;

    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public void assignRole(Role role){
        this.roles.add(role);
        role.getUsers().add(this);
    }
    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}
