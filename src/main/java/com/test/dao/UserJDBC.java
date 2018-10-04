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
        String sql = "select count(*) from Users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<User> getUsers() {
        String sql = "select * from Users";
        return jdbcTemplate.query(sql, this:: mapUser);
    }

    private User mapUser(ResultSet rs, int i) throws SQLException {
        return new User(
               rs.getInt("id"),
               rs.getString("name")
        );
    }

    @Override
    public String create(User user) {
        String sql = "INSERT INTO Users (id, name) VALUES(?,?)";
        jdbcTemplate.update(sql, user.getId(), user.getName());
        return "ok";
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
