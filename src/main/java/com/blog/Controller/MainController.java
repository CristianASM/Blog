package com.blog.Controller;

import com.blog.Model.User;
import com.blog.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {
    private final UserServiceImpl userService;
    @Autowired
    public MainController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/")
    public String home(Model model, Principal principal){
        if (principal != null) {
            String email = principal.getName();
            User user = userService.getUserByEmail(email);
            String userName = user.getUserName();
            model.addAttribute("userName", userName);
        }
        return "index";
    }
}
