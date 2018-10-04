package com.test.dao;

import com.test.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserJDBC implements UserDAO{

    private final JdbcTemplate jdbcTemplate;

    public UserJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer getCountUser() {
        String sql = "SELECT COUNT(*) FROM users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<User> getUsers() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, this:: mapUser);
    }

    private User mapUser(ResultSet rs, int i) throws SQLException {
        return new User(
               rs.getInt("id"),
               rs.getString("login"),
               rs.getString("password")
        );
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users (login, password) VALUES(?,?)";
        jdbcTemplate.update(sql, user.getLogin(), user.getPassword());
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
