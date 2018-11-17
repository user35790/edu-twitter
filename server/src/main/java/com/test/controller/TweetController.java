package com.test.controller;

import com.test.model.Tweet;
import com.test.model.User;
import com.test.model.UserRole;
import com.test.repos.TweetRepo;
import com.test.repos.UserRepo;
import com.test.service.TweetService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/tweet")
public class TweetController {

    private final TweetRepo tweetRepo;
    private final TweetService tweetService;
    private final UserRepo userRepo;

    public TweetController(TweetRepo tweetRepo, TweetService tweetService, UserRepo userRepo) {
        this.tweetRepo = tweetRepo;
        this.tweetService = tweetService;
        this.userRepo = userRepo;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/monitor")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {
        Iterable<Tweet> tweets = (!filter.isEmpty()) ? tweetRepo.findByAuthor_Username(filter) : tweetRepo.findAll();
        model.addAttribute("tweets", tweets);
        return "admin_tweets";
    }

    @PostMapping("/add")
    public String add(@Valid Tweet tweet,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam(required = false) MultipartFile file,
                      @AuthenticationPrincipal User author) {
        boolean isTweetAdd = tweetService.addTweet(tweet, author, file, bindingResult, model);
        if (!isTweetAdd) {
            model.addAttribute("tweets", tweetRepo.findByAuthor_Username(author.getUsername()));
            model.addAttribute("user", userRepo.findFirstById(author.getId()));
            model.addAttribute("isAuth", true);
            return "user_page";
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
        if (tweet.getAuthor().getId().equals(user.getId()) || user.getRoles().contains(UserRole.ADMIN)) {
            model.addAttribute("tweet", tweet);
            return "tweet_edit";
        } else {
            return "redirect:/user";
        }
    }

    @PostMapping("/edit")
    public String edit(@Valid Tweet tweet,
                       BindingResult bindingResult,
                       Model model,
                       @RequestParam(required = false) MultipartFile file) {
        boolean isTweetEdit = tweetService.editTweet(tweet, file, bindingResult, model);
        if (!isTweetEdit) {
            return "tweet_edit";
        } else {
            return "redirect:/user";
        }
    }

    // TODO: like-unlike + users-who-like list
//    @GetMapping("{tweet}/likes/list")
//    public String getListOfUsersHowLikes(@PathVariable Tweet tweet,
//                                         Model model) {
//        model.addAttribute("users", tweetRepo.);
//        model.addAttribute("title", "Users who like this tweet");
//        return "user_list";
//    }
}
