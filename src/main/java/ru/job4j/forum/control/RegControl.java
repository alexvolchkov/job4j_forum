package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Controller
public class RegControl {
    private final UserService users;

    public RegControl(UserService users) {
        this.users = users;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (!users.create(user)) {
            model.addAttribute("errorMessage", "Пользователь с таким именем уже существует");
            return "reg";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
