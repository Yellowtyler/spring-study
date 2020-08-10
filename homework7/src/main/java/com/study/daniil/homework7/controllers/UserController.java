package com.study.daniil.homework7.controllers;



import com.study.daniil.homework7.model.User;
import com.study.daniil.homework7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/remove/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.removeUserById(id);
        return "redirect:/users/all";
    }

    @GetMapping("/edit/{id}")
    public String getEditUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user-edit-page";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users/all";
    }

}
