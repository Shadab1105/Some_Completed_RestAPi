package com.dto;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String uid;
    private String name;
    private String email;
    private boolean enable;
    private Set<RoleDTO> roles;  // RoleDTO list instead of Role entity

    // Constructors, Getters, Setters
}

