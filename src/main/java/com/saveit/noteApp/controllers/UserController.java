package com.saveit.noteApp.controllers;

import com.saveit.noteApp.models.User;
import com.saveit.noteApp.models.enums.Role;
import com.saveit.noteApp.repo.UserRepository;
import com.saveit.noteApp.services.CustomAuthenticationProvider;
import com.saveit.noteApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserRepository userRepository;
    private final UserService userService;
    @Autowired
    private CustomAuthenticationProvider authenticationService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("title", "Сохраняй");
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/login?error";
        }
    }
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "Сохраняй");
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Пользователь с таким email уже существует");
            return "registration";
        }
    }
    /*@GetMapping("/login?error")
    public String loginError(Model model) {
        model.addAttribute("title", "Ошибка");
        return "loginError";
    }*/
}
