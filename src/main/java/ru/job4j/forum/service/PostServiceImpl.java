package ru.job4j.forum.service;

import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostStore;

import java.util.List;
import java.util.Optional;

public class PostServiceImpl implements PostService {
    private final PostStore store;

    public PostServiceImpl(PostStore store) {
        this.store = store;
    }

    @Override
    public List<Post> getAll() {
        return store.getAll();
    }

    @Override
    public Optional<Post> getById(int id) {
        return store.getById(id);
    }

    @Override
    public void save(Post post) {
        store.save(post);
    }
}
