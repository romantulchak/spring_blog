package com.blog.controller;

import com.blog.model.Post;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import com.blog.service.impl.UserServiceImpl;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class ProfileController {

    private UserServiceImpl userService;
    private PostRepository postRepository;

    @Autowired
    public ProfileController(UserServiceImpl userService, PostRepository postRepository){
        this.userService = userService;
        this.postRepository = postRepository;

    }


    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal User userFormDb){

        com.blog.model.User user = userService.findByUsername(userFormDb.getUsername());
        List<Post> posts = postRepository.findByUserOrderByIdDesc(user);
        model.addAttribute("name", user.getUsername());
        model.addAttribute("posts", posts);
        return "account/profile";
    }





}
