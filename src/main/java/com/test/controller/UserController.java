package com.test.controller;

import com.test.dao.UserDAO;
import com.test.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping("/new")
    public void createUser(@RequestParam(name="login", required=false, defaultValue="XXX") String login,
                              @RequestParam(name="password", required=false, defaultValue="123") String password) {
        userDAO.create(new User(login, password));
    }

    @RequestMapping("/get")
    public List<User> getUser(@RequestParam(name ="id", required=false, defaultValue="1") int id) {
        return userDAO.getUsers();
    }
}