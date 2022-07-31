package ru.job4j.forum.service;

import ru.job4j.forum.model.Post;

import java.util.*;

public interface PostService {

     List<Post> getAll();

     Optional<Post> getById(int id);

     void save(Post post);
}
