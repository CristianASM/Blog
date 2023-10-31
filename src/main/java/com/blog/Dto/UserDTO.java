package com.blog.Dto;

import com.blog.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private Collection<Role> roles;
}
