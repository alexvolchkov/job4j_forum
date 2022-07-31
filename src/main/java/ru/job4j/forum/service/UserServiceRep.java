package ru.job4j.forum.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.RoleRepository;
import ru.job4j.forum.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceRep implements UserDetailsService, UserService {
    private final UserRepository store;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    public UserServiceRep(UserRepository store, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.store = store;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username does not exist"));
    }

    @Override
    public List<User> getAll() {
        List<User> rsl = new ArrayList<>();
        store.findAll().forEach(rsl::add);
        return rsl;
    }

    @Override
    public boolean create(User user) {
        boolean rsl = false;
        Optional<User> userFromDb = findByUsername(user.getUsername());
        if (userFromDb.isEmpty()) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.addRole(roleRepository.findByName("ROLE_USER").get());
            store.save(user);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return store.findByUsername(username);
    }
}
