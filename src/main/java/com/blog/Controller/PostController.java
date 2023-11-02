package com.blog.Controller;

import com.blog.Model.Post;
import com.blog.Service.Impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostServiceImpl postService;
    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }
    @GetMapping("/newPost")
    public String newPost(Model model){
        Post newPost = new Post();
        model.addAttribute("newPost", newPost);
        return "post";
    }
    @PostMapping("/post")
    public String asd(@ModelAttribute("newPost") Post post){
        post.setCreatedDate(LocalDateTime.now());
        postService.newPost(post);
        return "redirect:/";
    }
}
