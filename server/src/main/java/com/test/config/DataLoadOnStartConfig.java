package com.test.config;

import com.test.model.Tweet;
import com.test.model.User;
import com.test.model.UserRole;
import com.test.repos.TweetRepo;
import com.test.repos.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoadOnStartConfig {

    private final UserRepo userRepo;
    private final TweetRepo tweetRepo;

    public DataLoadOnStartConfig(UserRepo userRepo, TweetRepo tweetRepo) {
        this.userRepo = userRepo;
        this.tweetRepo = tweetRepo;
    }

    @Bean
    public String loadData(){
        User admin = new User("admin", "admin", true, Collections.singleton(UserRole.ADMIN));
        User user_a = new User("a", "12345", true, Collections.singleton(UserRole.USER));
        User user_b = new User("b", "12345", true, Collections.singleton(UserRole.USER));
        User user_c = new User("c", "12345", true, Collections.singleton(UserRole.USER));
        userRepo.save(admin);
        userRepo.save(user_a);
        userRepo.save(user_b);
        userRepo.save(user_c);
        tweetRepo.save(new Tweet(admin, "test"));
        tweetRepo.save(new Tweet(admin, "test2"));
        tweetRepo.save(new Tweet(user_a, "a1"));
        tweetRepo.save(new Tweet(user_a, "a2"));
        tweetRepo.save(new Tweet(user_b, "b1"));
        tweetRepo.save(new Tweet(user_c, "c1"));

        return "ok, bro";
    }
}
