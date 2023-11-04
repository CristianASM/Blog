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
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProfileController {
    private final PostServiceImpl postService;
    private final UserServiceImpl userService;
    @Autowired
    public ProfileController(PostServiceImpl postService, UserServiceImpl userService) {
        this.postService = postService;
        this.userService = userService;
    }
    @ModelAttribute
    public void addUserNameToModel(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = userService.getUserByEmail(email);
            String userName = user.getUserName();
            model.addAttribute("userName", userName);
        }
    }
    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal){
        if (principal != null) {
            String email = principal.getName();
            User user = userService.getUserByEmail(email);
            List<Post> userPost = postService.getAllPost().stream()
                            .filter(post -> post.getUser().getId().equals(user.getId()))
                            .collect(Collectors.toList());
            model.addAttribute("userPost", userPost);
        }
        return "profile";
    }
    @GetMapping("/viewPost/{id}")
    public String viewPost(@PathVariable Long id, Model model){
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "viewPost";
    }
}
