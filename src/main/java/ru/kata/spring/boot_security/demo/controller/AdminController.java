package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String findAll(Model model ,@AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("allRoles", roleService.findAll());
        return "admin";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("allRoles", roleService.findAll());
        return "user";
    }

    @PostMapping()
    public String add(User user, String rawPassword) {
        user.setPassword(passwordEncoder.encode(rawPassword));
        userService.add(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.removeById(id);
        return "redirect:/admin";
    }

    @PutMapping("/{id}")
    public String update(User user, String rawPassword) {
        user.setPassword(passwordEncoder.encode(rawPassword));
        userService.update(user);
        return "redirect:/admin";
    }
}
