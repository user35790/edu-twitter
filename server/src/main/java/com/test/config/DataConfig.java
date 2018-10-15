package com.test.config;

import com.test.model.User;
import com.test.model.UserRole;
import com.test.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataConfig {

    @Autowired
    private UsersRepo usersRepo;

    @Bean
    public String loadData(){
        User user = new User();
        user.setUsername("a");
        user.setPassword("a");
        user.setActive(true);
        user.setRoles(Collections.singleton(UserRole.USER));
        usersRepo.save(user);
        return "ok, bro";
    }
}
