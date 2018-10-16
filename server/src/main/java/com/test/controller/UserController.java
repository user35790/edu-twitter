package com.test.controller;

import com.test.model.User;
import com.test.model.UserRole;
import com.test.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersRepo usersRepo;

    @GetMapping
    public String main(Map<String, Object> map){
        Iterable<User> users = usersRepo.findAll();
        map.put("users", users);
        return "adminUsers";
    }

    @PostMapping
    public String add(@RequestParam String login,
                      @RequestParam String password,
                      Map<String, Object> map){
        usersRepo.save(new User(login, password, true, Collections.singleton(UserRole.USER)));
        map.put("users", usersRepo.findAll());
        return "adminUsers";
    }


}