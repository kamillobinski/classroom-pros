package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //save ONE user
    public User saveUser(User user){
        return userRepository.save(user);
    }

    //allow to save MULTIPLE users
    public List<User> saveUsers(List <User> users){
        return userRepository.saveAll(users);
    }

    //return one particular user based on id
    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    //return all existing users
    public List <User>  getUsers(){
        return userRepository.findAll();
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public String deleteUser(int id){
        userRepository.deleteById(id);
        return "User was successfully removed!";
    }

    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return  userRepository.save(existingUser);
    }


}
