package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/users-view")
    public  String findAllUsers(Model model){
        List <User> allUsers = userService.getUsers();
        model.addAttribute("allUsers", allUsers);
        return "users-view";
    }

    @GetMapping("/user-manager")
    public String getUserManager(){
        return "user-manager";
    }

    @RequestMapping(value = "/add-user-action", method = RequestMethod.POST)
    public String addUser(Model model, @RequestParam("add-user-email-input") String email, @RequestParam("add-user-password-input")String password){

        if(email!="" && password!=""){

            User newUser = new User();
            newUser.setEmail(email);

            String hashedPassword;
            newUser.setPassword(bCryptPasswordEncoder.encode(password));

            /*
             *  Changed to admin role
             *  due to abandoned login and registration for regular user
             */
            Role userRole = roleRepository.findByRole("ADMIN");
            newUser.setRoles(new HashSet<>(List.of(userRole)));

            userService.saveUser(newUser);
        }else{
            if(email== "" || password == ""){
                model.addAttribute("message", "Could not add admin without data");
            }
        }
        return "user-manager";
    }
}
