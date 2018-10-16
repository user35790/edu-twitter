package com.test.controller;

import com.test.model.Tweet;
import com.test.model.User;
import com.test.repos.TweetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetRepo tweetRepo;

    @GetMapping
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Map<String, Object> map){

        Iterable<Tweet> tweets;
        if (!filter.isEmpty() && filter != ""){
            tweets = tweetRepo.findByAuthor_Username(filter);
        } else {
            tweets = tweetRepo.findAll();
        }

        map.put("tweets", tweets);

        return "adminTweets";
    }

    @PostMapping("/user")
    public String add(@RequestParam String text,
                      @AuthenticationPrincipal User author,
                      Map<String, Object> map){
        tweetRepo.save(new Tweet(author, text));

        Iterable<Tweet> tweets = tweetRepo.findByAuthor_Username(author.getUsername());
        map.put("tweets", tweets);

        return "home";
    }
}
