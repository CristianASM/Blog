package com.blog.Controller;

import com.blog.Model.Post;
import com.blog.Model.User;
import com.blog.Service.Impl.PostServiceImpl;
import com.blog.Service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.UUID;

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
    @ModelAttribute
    public void addUserNameToModel(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = userService.getUserByEmail(email);
            if (user != null) {
                String userName = user.getUserName();
                model.addAttribute("userName", userName);
            }
        }
    }
    @GetMapping("/newPost")
    public String newPost(Model model){
        Post newPost = new Post();
        model.addAttribute("newPost", newPost);
        return "post";
    }
    @PostMapping("/post")
    public String savePost(@Valid @ModelAttribute("newPost") Post post, Principal principal,
                           @RequestParam("file")MultipartFile file){
        if (principal != null) {
            String email = principal.getName();
            User user = userService.getUserByEmail(email);
            post.setUser(user);
            post.setCreatedDate(LocalDateTime.now());
            String sanitizedBody = sanitizeHTML(post.getBody());
            post.setBody(sanitizedBody);
            if (!file.isEmpty()) {
                try {
                    String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                    String relativePath = "/img/" + fileName;
                    Path absolutePath = Paths.get("src/main/resources/static" + relativePath);
                    Files.write(absolutePath, file.getBytes());
                    post.setImage(relativePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            postService.newPost(post);
        }
        return "redirect:/";
    }
    @GetMapping("/viewPost/{id}")
    public String viewPost(@PathVariable Long id, Model model){
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "viewPost";
    }
    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Long id, Model model){
        Post postToEdit = postService.getPost(id);
        model.addAttribute("edit", postToEdit);
        return "editPost";
    }
    @PostMapping("/edit/{id}")
    public String saveEdit(@Valid @PathVariable Long id, @ModelAttribute("edit") Post post,
                           @RequestParam("file") MultipartFile file){
        Post postToSave = postService.getPost(id);
        postToSave.setTitle(post.getTitle());
        postToSave.setBody(post.getBody());
        if (!file.isEmpty()) {
            try {
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                String relativePath = "/img/" + fileName;
                Path absolutePath = Paths.get("src/main/resources/static" + relativePath);
                Files.write(absolutePath, file.getBytes());
                postToSave.setImage(relativePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        postToSave.setCreatedDate(LocalDateTime.now());
        postService.updatePost(id, postToSave);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return "redirect:/";
    }
    private String sanitizeHTML(String inputHTML) {
        return Jsoup.clean(inputHTML, Whitelist.basic());
    }
}
