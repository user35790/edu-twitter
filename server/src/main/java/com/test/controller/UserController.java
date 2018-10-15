package com.test.controller;

import com.test.model.User;
import com.test.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersRepo usersRepo;

    @RequestMapping
    public String getHtml(Map<String, Object> map){
        Iterable<User> users = usersRepo.findAll();
        map.put("users", users);
        return "adminUsers";
    }


}