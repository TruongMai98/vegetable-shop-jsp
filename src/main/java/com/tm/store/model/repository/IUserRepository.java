package com.tm.store.model.repository;

import com.tm.store.model.entity.User;

import java.util.Optional;

public interface IUserRepository {
    Iterable<User> findAll();
    Optional<User> findById(Long id);
    void save(User user);
    void remove(Long id);
    Optional<User> login(String email, String password);
    User findByEmail(String email);
}
