package com.test.controller;

import com.test.model.Tweet;
import com.test.model.User;
import com.test.repos.TweetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetRepo tweetRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/monitor")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Map<String, Object> map){

        Iterable<Tweet> tweets;
        if (!filter.isEmpty() && filter != ""){
            tweets = tweetRepo.findByAuthor_Username(filter);
        } else {
            tweets = tweetRepo.findAll();
        }

        map.put("tweets", tweets);

        return "admin_tweets";
    }

    @PostMapping("/add")
    public String add(@RequestParam String text,
                      @RequestParam(name = "file", required = false) MultipartFile file,
                      @AuthenticationPrincipal User author,
                      Map<String, Object> map) throws IOException {
        Tweet tweet = new Tweet(author, text);

        if (file != null){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                System.out.println("AAAAAAAAAA");
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            tweet.setFilename(resultFilename);
        }

        tweetRepo.save(tweet);


        Iterable<Tweet> tweets = tweetRepo.findByAuthor_Username(author.getUsername());
        map.put("tweets", tweets);

        return "user_page";
    }
}
