package com.test.controller;

import com.test.model.Tweet;
import com.test.model.User;
import com.test.repos.TweetRepo;
import com.test.service.TweetService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/tweet")
public class TweetController {


    private final TweetRepo tweetRepo;
    private final TweetService tweetService;

    public TweetController(TweetRepo tweetRepo, TweetService tweetService) {
        this.tweetRepo = tweetRepo;
        this.tweetService = tweetService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/monitor")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {

        Iterable<Tweet> tweets;
        if (!filter.isEmpty()) {
            tweets = tweetRepo.findByAuthor_Username(filter);
        } else {
            tweets = tweetRepo.findAll();
        }

        model.addAttribute("tweets", tweets);

        return "admin_tweets";
    }

    @PostMapping("/add")
    public String add(@RequestParam String text,
                      @RequestParam(name = "file", required = false) MultipartFile file,
                      @AuthenticationPrincipal User author,
                      Model model) {
        Tweet tweet = new Tweet(author, text);

        if (!file.isEmpty()) {
            tweetService.addFileTweet(file, tweet);
        } else {
            tweetRepo.save(tweet);
        }
        Iterable<Tweet> tweets = tweetRepo.findByAuthor_Username(author.getUsername());
        model.addAttribute("tweets", tweets);

        return "redirect:/user";
    }


    @PostMapping("/delete/{tweetId}")
    public String delete(@PathVariable Integer tweetId) {
        tweetService.deleteTweet(tweetId);
        return "redirect:/user";
    }


    @GetMapping("/edit/{tweet}")
    public String getEditPage(@PathVariable Tweet tweet,
                              @AuthenticationPrincipal User user,
                              Model model) {
        if (tweet.getAuthor().getId().equals(user.getId())) {
            model.addAttribute("tweet", tweet);
            return "tweet_edit";
        } else {
            return "redirect:/user";
        }
    }

    @PostMapping("/edit/")
    public String edit(@AuthenticationPrincipal User user,
                       @RequestParam Integer id,
                       @RequestParam String text,
                       @RequestParam(name = "file", required = false) MultipartFile file,
                       Model model) {
        tweetService.editTweet(id, text, file);
        return "redirect:/user";
    }
}
