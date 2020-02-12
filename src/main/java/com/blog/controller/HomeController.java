package com.blog.controller;


import com.blog.model.Post;
import com.blog.model.User;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import com.blog.service.UserService;
import com.blog.service.impl.PostServiceImpl;
import com.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {
    private PostService postService;
    private UserServiceImpl userService;
    @Autowired
    public HomeController(PostService postService, UserServiceImpl userService){
        this.postService = postService;
        this.userService = userService;
    }

    @Value("${upload.path}")
    private String uploadPath;




    @GetMapping("/")
    public String home(Model model){

        Iterable<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("localDate", LocalDate.now());
        return "index";
    }



    @GetMapping("/createPost")
    public String createPost(){
        return "post/createPost";
    }

    @PostMapping("/addPost")
    public RedirectView addPost(@RequestParam String name, @RequestParam String text, @RequestParam("image")MultipartFile image, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userName) throws IOException {


        User user = userService.findByUsername(userName.getUsername());

        if (image !=null){
            File file = new File(uploadPath);
            if (!file.exists()){
                file.mkdir();
            }
            String uuidd = UUID.randomUUID().toString();

            String resultFileName = uuidd + "." + image.getOriginalFilename();

            image.transferTo(new File(uploadPath + "/" + resultFileName));
            Post post = new Post(name, text, resultFileName, user
            );
            postService.addPost(post);
        }

        return new RedirectView("/");

    }
    @PostMapping("/filter")
    public String findByName(@RequestParam String name, Model model){
        List<Post> posts = postService.getByName(name);
        model.addAttribute("posts", posts);

        return "index";
    }

}
