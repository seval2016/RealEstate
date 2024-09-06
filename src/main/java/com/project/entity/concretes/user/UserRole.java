package com.project.entity.concretes.user;


import com.fasterxml.jackson.annotation.JsonIgnore;

import com.project.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashSet;

import javax.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;


    @ManyToMany(mappedBy = "userRole")
    @JsonIgnore
    private Set<User> users;

}