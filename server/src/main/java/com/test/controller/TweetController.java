package com.test.controller;

import com.test.model.Tweet;
import com.test.model.User;
import com.test.repos.TweetRepo;
import com.test.service.TweetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/tweet")
public class TweetController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TweetController.class);

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
    public String add(@Valid Tweet tweet,
                      BindingResult bindingResult,
                      Model model,
                      @AuthenticationPrincipal User author,
                      @RequestParam(required = false) MultipartFile file) {
        if (bindingResult.hasErrors()) {
            LOGGER.warn("Errors in valid tweet add");
            Map<String, String> errorMap = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("tweet", tweet);
            return "user_page";
        } else {

            tweet.setAuthor(author);

            if (!file.isEmpty()) {
                tweetService.addFileTweet(file, tweet);
            } else {
                model.addAttribute("tweet", null);
                tweetRepo.save(tweet);
            }

            LOGGER.info("New tweet @{} added: text: {} ; file: {}", author.getUsername(),
                    tweet.getText(), tweet.getFilename());
        }


        Iterable<Tweet> tweets = tweetRepo.findByAuthor_Username(author.getUsername());
        model.addAttribute("tweets", tweets);

        return "redirect:/user";
    }


    @PostMapping("/delete/{tweetId}")
    public String delete(@PathVariable Integer tweetId) {
        tweetService.deleteTweet(tweetId);
        LOGGER.info("Tweet {} is delete", tweetId);
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

    @PostMapping("/edit")
    public String edit(@AuthenticationPrincipal User user,
                       @Valid Tweet tweet,
                       BindingResult bindingResult,
                       Model model,
                       @RequestParam(required = false) MultipartFile file) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("tweet", tweet);
            return "tweet_edit";
        } else {
            tweetService.editTweet(tweet, file);
            return "redirect:/user";
        }
    }

    @GetMapping("{tweet}/likes/list")
    public String getListOfUsersHowLikes(@PathVariable Tweet tweet,
                                         Model model) {
//        model.addAttribute("users", tweetRepo.);
        model.addAttribute("title", "Users who like this tweet");
        return "user_list";
    }
}
