package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceRep implements PostService {
    private final PostRepository store;

    public PostServiceRep(PostRepository store) {
        this.store = store;
    }

    @Override
    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        store.findAll().forEach(rsl::add);
        return rsl;
    }

    @Override
    public Optional<Post> getById(int id) {
        return store.findById(id);
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            post.setCreated(LocalDateTime.now());
        }
        store.save(post);
    }
}
