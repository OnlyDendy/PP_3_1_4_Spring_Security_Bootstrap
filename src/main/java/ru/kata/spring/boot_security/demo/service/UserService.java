package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    User add(User user);
    void update(User user);
    void removeById(Long id);
    List<User> findAll();
    User findById(Long id);
}
