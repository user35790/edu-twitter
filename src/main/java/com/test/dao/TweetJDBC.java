package com.test.dao;

import com.test.model.Tweet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class TweetJDBC implements TweetDAO {

    private final JdbcTemplate jdbcTemplate;

    public TweetJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Tweet tweet) {
        String sql = "INSERT INTO tweets (id_creator, text, date) VALUES (?,?,?)";
        jdbcTemplate.update(sql, tweet.getIdCreator(), tweet.getText(), new Date());
    }

    @Override
    public void updateText(int id, String text) {
        String sql = "UPDATE tweets SET text = ? WHERE id = ?";
        jdbcTemplate.update(sql, text, id);
    }

    @Override
    public void addLike(int id) {
        String sql = "UPDATE tweets SET likes = likes + 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void cancelLike(int id) {
        String sql = "UPDATE tweets SET likes = likes - 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM tweets WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
