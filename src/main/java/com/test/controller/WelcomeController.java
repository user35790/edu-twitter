package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WelcomeController {

    private String message = "Alex";

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("messageController", this.message);
        return "index";
    }
}
