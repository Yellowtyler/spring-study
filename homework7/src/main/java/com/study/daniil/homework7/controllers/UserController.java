package com.study.daniil.homework7.controllers;



import com.study.daniil.homework7.model.User;
import com.study.daniil.homework7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "all_users";
    }

    @PostMapping("/add")
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users/all";
    }
}
