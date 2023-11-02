package com.blog.Service;

import com.blog.Model.Role;

public interface RoleService {
    Role getRoleByName(String name);
    Role createdRole(String name);
}
