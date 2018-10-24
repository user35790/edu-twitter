package com.test.controller;

import com.test.model.User;
import com.test.model.UserRole;
import com.test.repos.TweetRepo;
import com.test.repos.UserRepo;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserRepo userRepo;
    private UserService userService;
    private TweetRepo tweetRepo;

    public UserController(UserRepo userRepo, UserService userService, TweetRepo tweetRepo) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.tweetRepo = tweetRepo;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/monitor")
    public String main(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "admin_users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/monitor/new")
    public String add(@RequestParam String login,
                      @RequestParam String password,
                      Model model) {
        userRepo.save(new User(login, password, true, Collections.singleton(UserRole.USER)));
        model.addAttribute("users", userRepo.findAll());
        return "admin_users";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam("userId") User user,
                           @AuthenticationPrincipal User userSession,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String about,
                           @RequestParam(required = false) boolean admin_role,
                           @RequestParam(required = false) boolean user_role,
                           Model model) {
        if (name != null) {
            user.setName(name);
        } else {
            user.setName(null);
        }
        if (about != null) {
            user.setAbout(about);
        }
        if (admin_role || user_role) {
            user.getRoles().clear();
            user.getRoles().add((admin_role) ? UserRole.ADMIN : UserRole.USER);
        }
        String message = userService.editUserProfile(user);
        if (!message.equals("")) {
            model.addAttribute("message", message);
            model = setModelEditUser(model, user);
            return "user_edit";
        }

        if (user.getId().equals(userSession.getId())) {
            return "redirect:/user";
        } else {
            return "redirect:/user/monitor";
        }

    }

    private Model setModelEditUser(Model model, User user){
        model.addAttribute("user", user);
        model.addAttribute("roles", UserRole.values());
        model.addAttribute("func_user", user.getRoles().iterator().next() == (UserRole.USER));
        model.addAttribute("func_admin", user.getRoles().iterator().next() == (UserRole.ADMIN));
        return model;
    }

    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable Integer userId,
                       @AuthenticationPrincipal User userSession,
                       Model model) {
        if ((userId.equals(0)) || (userSession.getRoles().iterator().next() == UserRole.ADMIN)) {
            User user = (userId.equals(0)) ?
                    userRepo.findFirstById(userSession.getId()) : userRepo.findFirstById(userId);
            model = setModelEditUser(model, user);
            return "user_edit";
        } else {
            return "redirect:/user";
        }
    }

    @GetMapping
    public String home(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("tweets", tweetRepo.findByAuthor_Username(user.getUsername()));
        model.addAttribute("userInfo", userRepo.findFirstById(user.getId()));
        return "user_page";
    }
}