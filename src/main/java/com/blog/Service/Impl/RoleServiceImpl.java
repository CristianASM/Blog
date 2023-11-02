package com.blog.Service.Impl;

import com.blog.Model.Role;
import com.blog.Repository.RoleRepository;
import com.blog.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role createdRole(String name) {
        Role role = roleRepository.findByName(name);
        if(role == null){
            role = new Role(name);
            role = roleRepository.save(role);
        }
        return role;
    }
}
