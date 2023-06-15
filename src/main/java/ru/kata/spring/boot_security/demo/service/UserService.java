package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
@Service
public interface UserService {

    User add(User user);
    void update(User user);
    void removeById(Long id);
    List<User> findAll();
    User findById(Long id);
}
