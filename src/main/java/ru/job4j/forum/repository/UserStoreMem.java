package ru.job4j.forum.repository;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.job4j.forum.model.Role;
import ru.job4j.forum.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class UserStoreMem implements UserStore {
    private final Map<Integer, User> users = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger();
    private final PasswordEncoder encoder;

    public UserStoreMem(PasswordEncoder encoder) {
        this.encoder = encoder;
        int index = id.incrementAndGet();
        User user = User.of(index, "user1", encoder.encode("123456"));
        user.addRole(Role.of(1, "ROLE_USER"));
        users.put(index, user);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) users.values();
    }

    @Override
    public boolean create(User user) {
        boolean rsl = false;
        Optional<User> userFromDb = findByUsername(user.getUsername());
        if (userFromDb.isEmpty()) {
            int index = id.incrementAndGet();
            user.setId(index);
            user.setPassword(encoder.encode(user.getPassword()));
            user.addRole(Role.of(1, "ROLE_USER"));
            users.put(index, user);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.values()
                .stream()
                .filter(el -> username.equals(el.getUsername()))
                .findFirst();
    }
}
