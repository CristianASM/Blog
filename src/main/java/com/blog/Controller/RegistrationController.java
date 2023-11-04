package com.blog.Controller;

import com.blog.Dto.UserDTO;
import com.blog.Service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserServiceImpl userService;
    @Autowired
    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @ModelAttribute("user")
    public UserDTO userRegistrationDto() {
        return new UserDTO();
    }
    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }
    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/registration?error";
        }
        userService.saveUser(userDTO);
        return "redirect:/registration?success";
    }

}
