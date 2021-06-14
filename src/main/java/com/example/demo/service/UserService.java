package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //save ONE user
    public Model saveUser(Model model, String email, String password){
        if(!email.equals("") && !password.equals("")){

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

            userRepository.save(newUser);
        }else{
            if(email.equals("") || password.equals("")){
                model.addAttribute("message", "Could not add admin without data");
            }
        }
        return model;
    }

    public User saveUserWithReqBody(User user) {
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
    public List<User> getUsers(){
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
