package ru.job4j.forum.repository;

import ru.job4j.forum.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostStore {
    List<Post> getAll();

    Optional<Post> getById(int id);

    void save(Post post);
}
