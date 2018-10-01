package com.test.hello;

import com.test.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private static int count;

    @RequestMapping("/new")
    public User greeting(@RequestParam(name="name", required=false, defaultValue="Username") String name) {
        count++;
        return new User(count, name);

    }

}