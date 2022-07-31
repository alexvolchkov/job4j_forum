package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {
    private final PostService posts;

    public PostControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", posts.getById(id).orElse(new Post()));
        return "post";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", posts.getById(id).orElse(new Post()));
        return "edit";
    }

    @GetMapping("/addPost")
    public String create(@ModelAttribute Post post) {
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        posts.save(post);
        return "redirect:/index";
    }
}
