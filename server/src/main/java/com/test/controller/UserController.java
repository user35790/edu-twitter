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
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private TweetRepo tweetRepo;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/monitor")
    public String main(Map<String, Object> map){
        Iterable<User> users = userRepo.findAll();
        map.put("users", users);
        return "admin_users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/monitor/new")
    public String add(@RequestParam String login,
                      @RequestParam String password,
                      Map<String, Object> map){
        userRepo.save(new User(login, password, true, Collections.singleton(UserRole.USER)));
        map.put("users", userRepo.findAll());
        return "admin_users";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam("userId") User user,
                           @RequestParam String username,
                           @RequestParam(required = false) boolean admin_role,
                           @RequestParam(required = false) boolean user_role){

        if (userService.isCorrectUsername(username)){
            user.setUsername(username);
        }

        if (admin_role || user_role){
            user.getRoles().clear();
            user.getRoles().add((admin_role)? UserRole.ADMIN : UserRole.USER);
        }
        userRepo.save(user);
        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String editUser(@AuthenticationPrincipal User userSession, Model model){
        User user = userRepo.findFirstById(userSession.getId());
        model.addAttribute("user", user);
        model.addAttribute("roles", UserRole.values());
        model.addAttribute("func_user", user.getRoles().iterator().next() == (UserRole.USER));
        model.addAttribute("func_admin", user.getRoles().iterator().next() == (UserRole.ADMIN));
        return "user_edit";
    }

    @GetMapping("/edit/{userEdit}")
    public String edit(@PathVariable User userEdit,
                       @AuthenticationPrincipal User userSession,
                       Model model){
        User user;
        if ((userSession.getRoles().iterator().next() == UserRole.ADMIN) ||
                (userEdit.getId().equals(userSession.getId()))){
            user = userEdit;
        } else {
            user = userSession;
        }
        model.addAttribute("user", user);
        model.addAttribute("roles", UserRole.values());
        model.addAttribute("func_user", user.getRoles().iterator().next() == (UserRole.USER));
        model.addAttribute("func_admin", user.getRoles().iterator().next() == (UserRole.ADMIN));
        return "user_edit";
    }

    @GetMapping
    public String home(@AuthenticationPrincipal User user,
                       Map<String, Object> map) {
        map.put("tweets", tweetRepo.findByAuthor_Username(user.getUsername()));
        return "user_page";
    }
}