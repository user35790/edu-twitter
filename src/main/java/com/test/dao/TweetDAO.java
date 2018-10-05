package com.test.dao;

import com.test.model.Tweet;

public interface TweetDAO {

    void create(Tweet tweet);

    void updateText(int id, String text);

    void addLike(int id);

    void cancelLike(int id);

    void delete(int id);
}
