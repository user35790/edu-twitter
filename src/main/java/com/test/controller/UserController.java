package com.test.controller;

import com.test.dao.UserDAO;
import com.test.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private static int count = 0;

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping("/new")
    public Integer createUser(@RequestParam(name="name", required=false, defaultValue="Username") String name) {
        count++;
        userDAO.create(new User(count, name));
        return count;
    }

    @RequestMapping("/get")
    public List<User> getUser(@RequestParam(name ="id", required=false, defaultValue="1") int id) {
        return userDAO.getUsers();
    }
}