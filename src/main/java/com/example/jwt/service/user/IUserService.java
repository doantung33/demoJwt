package com.example.jwt.service.user;

import com.example.jwt.model.User;
import com.example.jwt.service.IService;

public interface IUserService extends IService<User> {
    boolean existsByUsername(String username);

    boolean existsByEmail (String email);
}
