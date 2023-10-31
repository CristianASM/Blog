package com.blog.Service;

import com.blog.Dto.UserDTO;
import com.blog.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User saveUser (UserDTO userDTO);
}
