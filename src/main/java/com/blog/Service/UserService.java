package com.blog.Service;

import com.blog.Dto.UserDTO;
import com.blog.Model.User;

public interface UserService {
    User saveUser (UserDTO userDTO);
}
