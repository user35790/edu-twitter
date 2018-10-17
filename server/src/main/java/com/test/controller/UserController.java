package com.test.controller;

import com.test.model.User;
import com.test.model.UserRole;
import com.test.repos.TweetRepo;
import com.test.repos.UsersRepo;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private TweetRepo tweetRepo;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/monitor")
    public String main(Map<String, Object> map){
        Iterable<User> users = usersRepo.findAll();
        map.put("users", users);
        return "admin_users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/monitor/new")
    public String add(@RequestParam String login,
                      @RequestParam String password,
                      Map<String, Object> map){
        usersRepo.save(new User(login, password, true, Collections.singleton(UserRole.USER)));
        map.put("users", usersRepo.findAll());
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
        usersRepo.save(user);
        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String edit(@AuthenticationPrincipal User user, Map<String, Object> map){
        map.put("user", user);
        map.put("roles", UserRole.values());
        map.put("func_user", user.getRoles().iterator().next() == (UserRole.USER));
        map.put("func_admin", user.getRoles().iterator().next() == (UserRole.ADMIN));
        return "user_edit";
    }

    @GetMapping("/hello")
    public String greeting(@AuthenticationPrincipal User user,
                           Map<String, Object> map) {
        map.put("name", user.getUsername());
        return "user_hello";
    }

    // AuthenticationPrincipal user не обновляется, сложно использовать
    @GetMapping
    public String home(@AuthenticationPrincipal User user,
                       Map<String, Object> map) {
        map.put("tweets", tweetRepo.findByAuthor_Username(user.getUsername()));
        return "user_page";
    }
}