package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    /*
    Works in progress, after removing RestController some methods may not work properly yet
     */

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public User addUser (@RequestBody User user){
        return userService.saveUserWithReqBody(user);
    }

    @PostMapping("/addUsers")
    public List<User> addUsers (@RequestBody List<User> users){
        return userService.saveUsers(users);
    }

    @GetMapping("/userById/{id}")
    public User findUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public  List<User> findAllUsers(){
        return userService.getUsers();
    }

    @GetMapping("/userByEmail/{email}")
    public User findUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @PutMapping("/update")
    public  User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }



}
