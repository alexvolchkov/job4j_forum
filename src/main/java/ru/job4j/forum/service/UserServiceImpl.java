package ru.job4j.forum.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserStore;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserStore store;

    public UserServiceImpl(UserStore store) {
        this.store = store;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username does not exist"));
    }

    @Override
    public List<User> getAll() {
        return store.getAll();
    }

    @Override
    public boolean create(User user) {
        return store.create(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return store.findByUsername(username);
    }
}
