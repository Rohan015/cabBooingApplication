package com.ar.cabBooingApplication.entities;

import com.ar.cabBooingApplication.entities.enums.Role;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    @Nonnull
    private String email;
    private String password;
    @ElementCollection(fetch = FetchType.LAZY) //it will create another table for the roles only
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}

