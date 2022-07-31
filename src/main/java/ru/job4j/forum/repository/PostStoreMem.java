package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostStoreMem implements PostStore {
    private final Map<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public PostStoreMem() {
        int index = id.incrementAndGet();
        posts.put(index, Post.of(index, "Продаю машину ладу 01."));
    }

    @Override
    public List<Post> getAll() {
        return posts.values().stream().toList();
    }

    @Override
    public Optional<Post> getById(int id) {
        return Optional.ofNullable(posts.get(id));
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(id.incrementAndGet());
            post.setCreated(LocalDateTime.now());
        }
        posts.put(post.getId(), post);
    }
}
