package com.example.demo.controller;
import com.example.demo.entity.Plan;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.PlanService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    // Sign in page
    @RequestMapping(value={"/", "/login", "sign-in"} , method = RequestMethod.GET)
    public String getSignIn() {
        return "sign-in";
    }

    // Sign up
    @RequestMapping("/sign-up")
    public String getSignUp() {
        return "sign-up";
    }

    // Homepage
    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String getHomepage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.getUserByEmail(auth.getName());

        // Collect only admin role
        HashSet<Role> loggedUserRoles = new HashSet<>(loggedUser.getRoles());
        String log_user_role = "";
        for(Role role : loggedUserRoles){
            if(role.getRole().equals("ADMIN")) log_user_role = String.valueOf(role.getRole());
        }

        model.addAttribute("log_user_mail", loggedUser.getName());
        model.addAttribute("log_user_role", log_user_role);
        return "homepage";
    }

    // Admin panel old version
    @RequestMapping("/admin-panel-old")
    public String getAdminPanelOld() {
        return "admin-panel-old";
    }

    // All plans
    @RequestMapping("/plans")
    public String getPlans(Model model) {
        // get plans
        List<Plan> allPlans = planService.getAllPlans();

        // get logged user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.getUserByEmail(auth.getName());

        // Collect only admin role
        HashSet<Role> loggedUserRoles = new HashSet<>(loggedUser.getRoles());
        String log_user_role = "";
        for(Role role : loggedUserRoles){
            if(role.getRole().equals("ADMIN")) log_user_role = String.valueOf(role.getRole());
        }

        model.addAttribute("allPlans", allPlans);
        model.addAttribute("log_user_mail", loggedUser.getName());

        model.addAttribute("log_user_role", log_user_role);
        return "plans";
    }

    // All plans
    @RequestMapping("/plans-read-only")
    public String getPlansReadOnly(Model model) {
        // get plans
        List<Plan> allPlans = planService.getAllPlans();

        // get logged user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.getUserByEmail(auth.getName());

        // Collect only admin role
        HashSet<Role> loggedUserRoles = new HashSet<>(loggedUser.getRoles());
        String log_user_role = "";
        for(Role role : loggedUserRoles){
            if(role.getRole().equals("ADMIN")) log_user_role = String.valueOf(role.getRole());
        }

        model.addAttribute("allPlans", allPlans);
        model.addAttribute("log_user_mail", loggedUser.getName());

        model.addAttribute("log_user_role", log_user_role);
        return "plans-read-only";
    }

    //Admin panel new version
    @RequestMapping("/admin-panel")
    public String getAdminPanel() {
        return "admin-panel";
    }

    // 403 page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String get403Page() {
        return "error/403";
    }

    // 404 page
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String get404Page() {
        return "error/404";
    }
}
