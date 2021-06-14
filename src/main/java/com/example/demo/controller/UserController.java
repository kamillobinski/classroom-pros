package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users-view")
    public String findAllUsers(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("allUsers", users);
        return "users-view";
    }

    @GetMapping("/user-manager")
    public String getUserManager(){
        return "user-manager";
    }

    @PostMapping(value = "/add-user-action")
    public String addUser(Model model, @RequestParam("add-user-email-input") String email, @RequestParam("add-user-password-input")String password){
        userService.saveUser(model, email, password);
        return "user-manager";
    }
}
