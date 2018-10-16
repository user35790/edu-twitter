package com.test.controller;

import com.test.model.User;
import com.test.repos.TweetRepo;
import com.test.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private TweetRepo tweetRepo;

    @GetMapping("/hello")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            @AuthenticationPrincipal User user,
            Map<String, Object> map) {
        map.put("name", user.getUsername());
        return "hello";
    }

    @GetMapping("/")
    public String greeting() {
        return "main";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal User user,
                       Map<String, Object> map) {
        map.put("tweets", tweetRepo.findByAuthor_Username(user.getUsername()));
        return "home";
    }
}
