package com.test.repos;

import com.test.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepo extends JpaRepository<Tweet, Integer> {

    List<Tweet> findByTag(String tag);
}
