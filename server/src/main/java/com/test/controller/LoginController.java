package com.test.controller;

import com.test.model.User;
import com.test.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String main() {
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Model model) {
        String message = userService.addUser(user);

        if (!message.isEmpty()) {
            model.addAttribute("message", message);
            return "registration";
        } else {
            model.addAttribute("message", "User successfully create");
            return "login";
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {
        boolean isActivateUser = userService.activateUser(code);
        if (isActivateUser) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found");
        }
        return "login";
    }
}
