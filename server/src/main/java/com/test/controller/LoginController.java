package com.test.controller;

import com.test.model.User;
import com.test.model.dto.CaptchaResponceDto;
import com.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class LoginController {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;
    private final RestTemplate restTemplate;

    @Value("${recaptha.secret}")
    private String recaptchaSecret;

    public LoginController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
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
        String url = String.format(CAPTCHA_URL, recaptchaSecret, captchaResponse);
        CaptchaResponceDto response = restTemplate.postForObject(url, Collections.emptyList(),
                CaptchaResponceDto.class);

        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
            model.addAttribute("user", user);
            return "registration";
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("user", user);
            return "registration";
        }

        Map<String, Object> result = userService.addUser(user);
        if (result.isEmpty()) {
            model.addAttribute("message", "User successfully create");
            LOGGER.info("User @%{} create", user.getUsername());
            return "login";
        } else {
            model.mergeAttributes(result);
            LOGGER.warn("User not create");
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
