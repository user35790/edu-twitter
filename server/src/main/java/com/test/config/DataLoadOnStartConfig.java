package com.test.config;

import com.test.model.Tweet;
import com.test.model.User;
import com.test.model.UserRole;
import com.test.repos.TweetRepo;
import com.test.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoadOnStartConfig {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private TweetRepo tweetRepo;

    @Bean
    public String loadData(){
        User admin = new User("admin", "admin", true, Collections.singleton(UserRole.ADMIN));
        User user_a = new User("a", "a", true, Collections.singleton(UserRole.USER));
        User user_b = new User("b", "b", true, Collections.singleton(UserRole.USER));
        User user_c = new User("c", "c", true, Collections.singleton(UserRole.USER));
        usersRepo.save(admin);
        usersRepo.save(user_a);
        usersRepo.save(user_b);
        usersRepo.save(user_c);
        tweetRepo.save(new Tweet(admin, "test"));
        tweetRepo.save(new Tweet(admin, "test2"));
        tweetRepo.save(new Tweet(user_a, "a1"));
        tweetRepo.save(new Tweet(user_a, "a2"));
        tweetRepo.save(new Tweet(user_b, "b1"));
        tweetRepo.save(new Tweet(user_c, "c1"));

        return "ok, bro";
    }
}
