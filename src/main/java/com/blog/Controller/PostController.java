package com.blog.Controller;

import com.blog.Model.Post;
import com.blog.Model.User;
import com.blog.Service.Impl.PostServiceImpl;
import com.blog.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostServiceImpl postService;
    private final UserServiceImpl userService;
    @Autowired
    public PostController(PostServiceImpl postService, UserServiceImpl userService) {
        this.postService = postService;
        this.userService = userService;
    }
    @GetMapping("/newPost")
    public String newPost(Model model, Principal principal){
        Post newPost = new Post();
        model.addAttribute("newPost", newPost);
        if (principal != null) {
            String email = principal.getName();
            User user = userService.getUserByEmail(email);
            String userName = user.getUserName();
            model.addAttribute("userName", userName);
        }
        return "post";
    }
    @PostMapping("/post")
    public String asd(@ModelAttribute("newPost") Post post){
        post.setCreatedDate(LocalDateTime.now());
        postService.newPost(post);
        return "redirect:/";
    }
}
