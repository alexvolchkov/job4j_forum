package ru.job4j.forum.repository;

import ru.job4j.forum.model.User;

import java.util.List;
import java.util.Optional;

public interface UserStore {

     List<User> getAll();

     boolean create(User user);

     Optional<User> findByUsername(String username);
}
