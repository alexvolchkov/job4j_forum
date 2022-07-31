package ru.job4j.forum.service;

import ru.job4j.forum.model.User;

import java.util.*;

public interface UserService {

     List<User> getAll();

     boolean create(User user);

     Optional<User> findByUsername(String username);
}
