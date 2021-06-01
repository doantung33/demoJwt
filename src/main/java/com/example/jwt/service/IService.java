package com.example.jwt.service;

import java.util.List;

public interface IService<J> {
    List<J> findAll();
    J findById (Long id);
    J save (J j);
    void delete (Long id);
}
