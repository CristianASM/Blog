package com.blog.Controller;

import com.blog.Dto.UserDTO;
import com.blog.Model.User;
import com.blog.Service.Impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private final UserServiceImpl userService;
    @Autowired
    public UserRegistrationController(UserServiceImpl userService, ModelMapper modelMapper) {
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
    public String registerUser(@ModelAttribute("user") UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "redirect:/registration?success";
    }

}
