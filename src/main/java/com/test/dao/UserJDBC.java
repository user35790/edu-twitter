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
    public Integer getCountUsers() {
        String sql = "SELECT COUNT(*) FROM users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public boolean isLoginNotExist(String login) {
        String sql = "SELECT COUNT(*) FROM users WHERE login = ?";
        Integer n = jdbcTemplate.queryForObject(sql, Integer.class, login);
        return ((n == null) || (n == 0)) ? true : false;
    }

    @Override
    public List<User> getUsers() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, this::mapUsers);
    }

    @Override
    public User getUser(int id) {
        String sql = "SELECT * FROM Users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapUser, id);
    }

    @Override
    public void createUser(User user, String password) {
        String sql = "INSERT INTO users (login, password, name, sex) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, user.getLogin(), password, user.getName(), user.getSex());
    }

    @Override
    public void updateUserInfo(User user) {
        String sql = "UPDATE users SET name = ?, login = ?, date_birthday = ?, sex = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getLogin(), user.getDateBirthday(), user.getSex(), user.getId());
    }

    @Override
    public void updateUserPassword(int id, String password) {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        jdbcTemplate.update(sql, password, id);
    }

    @Override
    public void updateUserStatus(int id, String status) {
        String sql = "UPDATE users SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, id);
    }

    @Override
    public void updateUserImage(int id, String image) {
        String sql = "UPDATE users SET image = ? WHERE id = ?";
        jdbcTemplate.update(sql, image, id);
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private User mapUser(ResultSet rs, int i) throws SQLException{
        return new User(
                rs.getString("login"),
                rs.getString("name"),
                rs.getDate("date_birthday"),
                rs.getBoolean("sex"),
                rs.getString("image"),
                rs.getString("status")
        );
    }

    private User mapUsers(ResultSet rs, int i) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("login"),
                rs.getString("name"),
                rs.getString("image")
        );
    }
}
