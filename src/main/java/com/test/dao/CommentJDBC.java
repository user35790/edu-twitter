package com.test.dao;

import com.test.model.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class CommentJDBC implements CommentDAO {

    private final JdbcTemplate jdbcTemplate;

    public CommentJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Comment comment) {
        String sql = "INSERT INTO comments (id_creator, id_tweet, text, date) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, comment.getIdCreator(), comment.getIdTweet(), comment.getText(), new Date());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM comments WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public void updateText(int id, String text) {
        String sql = "UPDATE comments SET text = ? WHERE id = ?";
        jdbcTemplate.update(sql, text, id);
    }

    @Override
    public void addLike(int id) {
        String sql = "UPDATE comments SET likes = likes + 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void cancelLike(int id) {
        String sql = "UPDATE comments SET likes = likes - 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
