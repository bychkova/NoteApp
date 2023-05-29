package com.saveit.noteApp.controllers;

import com.saveit.noteApp.models.User;
import com.saveit.noteApp.models.enums.Role;
import com.saveit.noteApp.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserRepository userRepository;
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Сохраняй");
        return "login";
    }
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "Сохраняй");
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }

}
