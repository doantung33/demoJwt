package com.example.jwt.service.role;

import com.example.jwt.model.Role;
import com.example.jwt.service.IService;

import java.util.Optional;

public interface IRoleService extends IService<Role> {
    Optional<Role> findByName(String name);
}
