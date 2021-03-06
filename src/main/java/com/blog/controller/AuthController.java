package com.blog.controller;

import com.blog.model.Role;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import com.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;

@Controller
public class AuthController {

    private UserServiceImpl userServiceImpl;

    @Autowired
    public AuthController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping("/registration")
    public String registration(){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public RedirectView addUser(@ModelAttribute(value = "user") User user){

        //User userFromDb = userServiceImpl.

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        boolean d = userServiceImpl.addUser(user);
        if (d){
            return new RedirectView("/login");
        }else {
            return new RedirectView("/registration");
        }



    }

    @GetMapping("/out")
    public String logout(){
        return "auth/logout";
    }


}
