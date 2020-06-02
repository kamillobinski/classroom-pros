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
    public  String findAllUsers(Model model){
        List <User> allUsers = userService.getUsers();
        model.addAttribute("allUsers", allUsers);
        return "users-view";
    }


}
