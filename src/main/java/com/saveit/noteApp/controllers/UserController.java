package com.saveit.noteApp.controllers;

import com.saveit.noteApp.models.User;
import com.saveit.noteApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    //@Autowired
    private final UserService userService;
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
        /*if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }*/
        userService.createUser(user);
        return "redirect:/login";

    }

   /* @PostMapping("/login")
    public String loginInto(Model model) {
        //model.addAttribute("title", "Сохраняй");
        return "redirect:/";
    }*/

}
