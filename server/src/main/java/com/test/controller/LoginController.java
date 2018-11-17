package com.test.controller;

import com.test.model.User;
import com.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class LoginController {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String main() {
        return "registration";
    }

    @PostMapping
    public String addUser(@RequestParam("g-recaptcha-response") String captchaResponse,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        boolean isAddUser = userService.addUser(user, bindingResult, model, captchaResponse);
        if (isAddUser) {
            model.addAttribute("message", "User successfully create");
            LOGGER.info("User @{} has registration", user.getUsername());
            return "login";
        } else {
            LOGGER.warn("User not registration");
            return "registration";
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
