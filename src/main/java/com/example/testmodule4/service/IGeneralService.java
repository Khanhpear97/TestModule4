package com.example.testmodule4.service;

import com.example.testmodule4.model.City;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();
    Optional<T> findById(Long id);
    void save (T t);
    void remove(Long id);

    Iterable<T> findByName(String name);
}
